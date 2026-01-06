package br.com.vemprofut.services.implementacao;

import static br.com.vemprofut.models.enuns.TipoCartao.*;

import br.com.vemprofut.controllers.response.CartoesResumoResponseDTO;
import br.com.vemprofut.mappers.ICartoesMapper;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartaoCountProjection;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.repositories.CartoesRepository;
import br.com.vemprofut.services.ICartoesService;
import br.com.vemprofut.services.IFutService;
import br.com.vemprofut.services.query.ICartoesQueryService;
import br.com.vemprofut.services.query.IPartidasQueryService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartoesService implements ICartoesService {

  @Autowired private CartoesRepository repository;

  @Autowired private ICartoesMapper mapper;

  @Autowired private ICartoesQueryService queryService;

  @Autowired private IPeladeiroQueryService peladeiroQueryService;

  @Autowired
  @Lazy // Isso diz ao Spring: “injete essa dependência só quando for realmente usada”, quebrando o
  // ciclo de inicialização.
  private IPartidasQueryService partidasQueryService;

  @Autowired @Lazy private IFutService futService;

  @Autowired
  @Qualifier("defaultExecutor")
  Executor executor;

  /*
  Executor = uma forma diferente de tornar async
  em vez de usar @Async("defaultExecutor").
   */

  @Override
  @Transactional
  public CompletableFuture<CartoesDTO> create(CartoesDTO dto) {
    return CompletableFuture.supplyAsync(
        () -> {
          queryService.verifyEntitiesExist(dto);
          CartoesModel model = mapper.toModel(dto);
          CartoesModel saved = repository.save(model);
          return mapper.toDTO(saved);
        },
        executor);
  }

  @Override
  @Transactional(readOnly = true)
  public CompletableFuture<List<CartoesDTO>> findAll() {
    return CompletableFuture.supplyAsync(
        () -> {
          return repository.findAll().stream().map(mapper::toDTO).toList();
        },
        executor);
  }

  @Override
  @Transactional(readOnly = true)
  public CompletableFuture<CartoesDTO> findById(Long id) {

    return CompletableFuture.supplyAsync(
        () -> {
          var cartao = queryService.verityCartoesExist(id);
          return mapper.toDTO(cartao);
        },
        executor);
  }

  @Override
  @Transactional(readOnly = true)
  public CompletableFuture<CartoesModel> findByIdModel(Long id) {
    return CompletableFuture.supplyAsync(
        () -> {
          return queryService.verityCartoesExist(id);
        },
        executor);
  }

  @Override
  @Transactional(readOnly = true)
  public CompletableFuture<List<CartoesDTO>> findByPeladeiro(Long id) {
    /* PeladeiroModel peladeiroModel = peladeiroQueryService.verifyPeladeiroExist(id);
    return CompletableFuture.completedFuture( <--- sincrono
            repository.findByPeladeiro(peladeiroModel).stream().map(mapper::toDTO).toList());
    */
    // async
    return peladeiroQueryService
        .verifyPeladeiroExist(id)
        .thenApply(
            peladeiroModel ->
                repository.findByPeladeiro(peladeiroModel).stream().map(mapper::toDTO).toList());
  }

  @Override
  @Transactional(readOnly = true)
  public CompletableFuture<List<CartoesDTO>> findByPartida(Long id) {
    return partidasQueryService
        .verifyPartidaExistWithRetorn(id)
        .thenApply(
            partidasModel ->
                repository.findByPartida(partidasModel).stream().map(mapper::toDTO).toList());
  }

  @Override
  @Transactional(readOnly = true)
  public CompletableFuture<List<CartoesDTO>> findByFut(Long id) {
    return futService
        .findByIdModel(id)
        .thenApply(futModel -> repository.findByFut(futModel).stream().map(mapper::toDTO).toList());
  }

  public CompletableFuture<CartoesResumoResponseDTO> contarCartoesPeladeiro(Long peladeiroId) {

    return CompletableFuture.supplyAsync(
        () -> {
          List<CartaoCountProjection> resultados = repository.countByTipoAndPeladeiro(peladeiroId);
          return montarResumo(resultados);
        });
  }

  public CompletableFuture<CartoesResumoResponseDTO> contarCartoesFut(Long futId) {

    return CompletableFuture.supplyAsync(
        () -> {
          List<CartaoCountProjection> resultados = repository.countByTipoAndFut(futId);
          return montarResumo(resultados);
        });
  }

  private CartoesResumoResponseDTO montarResumo(List<CartaoCountProjection> resultados) {
    CartoesResumoResponseDTO dto = new CartoesResumoResponseDTO();

    for (CartaoCountProjection proj : resultados) {
      switch (proj.getTipo()) {
        case AZUL -> dto.setAzul(proj.getQuantidade().intValue());
        case AMARELO -> dto.setAmarelo(proj.getQuantidade().intValue());
        case VERMELHO -> dto.setVermelho(proj.getQuantidade().intValue());
      }
    }
    return dto;
  }
}
