package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.controllers.request.SavePartidaRequestDTO;
import br.com.vemprofut.controllers.response.SavePartidasResponseDTO;
import br.com.vemprofut.mappers.ICartoesMapper;
import br.com.vemprofut.mappers.IPartidasMapper;
import br.com.vemprofut.mappers.IPeladeiroMapper;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.repositories.PartidasRepository;
import br.com.vemprofut.services.*;
import br.com.vemprofut.services.query.IPartidasQueryService;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidasService implements IPartidasService {

  @Autowired private IPartidasMapper mapperPartidas;

  @Autowired private PartidasRepository repository;

  @Autowired private IPartidasQueryService queryService;

  @Autowired private IGolsPartidaService golsService;

  @Autowired private ICartoesService cartoesService;

  @Autowired private IPeladeiroService peladeiroService;

  @Autowired private IPeladeiroMapper IPeladeiroMapper;

  @Autowired private ICartoesMapper ICartoesMapper;

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<SavePartidasResponseDTO> create(
      SavePartidaRequestDTO requestDTO, FutModel futModel) {
    PartidasModel partidasModel = new PartidasModel(requestDTO.reservas(), futModel);
    SavePartidasResponseDTO response = mapperPartidas.toResponse(repository.save(partidasModel));
    return CompletableFuture.completedFuture(response);
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<PartidasDTO> findById(Long id) {
    return queryService.verifyPartidaExistWithRetorn(id).thenApply(mapperPartidas::toDTO);
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<PartidasModel> findByIdModel(Long id) {
    return queryService.verifyPartidaExistWithRetorn(id);
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<Void> addGols(PeladeiroDTO peladeiroDTO, PartidasDTO partidasDTO) {
    GolsPartidaModel gol = golsService.create(peladeiroDTO, partidasDTO);

    PartidasModel partida = mapperPartidas.toModel(partidasDTO);
    partida.getGolsPartida().add(gol);

    repository.save(partida);
    return CompletableFuture.completedFuture(null);
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<Void> addPeladeiros(Long peladeiroId, PartidasDTO partidasDTO) {
    /*
    Aqui você espera o CompletableFuture<PeladeiroModel> terminar.
    Quando o PeladeiroModel estiver disponível, adiciona na partida e salva.
    O método retorna um CompletableFuture<Void> que completa quando tudo terminar.
     */
    return peladeiroService
        .findByIdModel(peladeiroId)
        .thenAccept(
            peladeiroModel -> {
              PartidasModel partida = mapperPartidas.toModel(partidasDTO);
              partida.getPeladeiros().add(peladeiroModel);
            });
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<Void> addCartoes(Long cartaoId, PartidasDTO partidasDTO) {
    // TODO: mudar o DTO para Long de cartoes.
    /* ---> sincrono
    CartoesModel cartoesModel = cartoesService.findByIdModel(cartaoId);
    PartidasModel partida = mapperPartidas.toModel(partidasDTO);
    partida.getCartoes().add(cartoesModel);
    return repository.save(partida); */
    // Async
    return cartoesService
        .findByIdModel(cartaoId)
        .thenAccept(
            cartoesModel -> {
              PartidasModel partida = mapperPartidas.toModel(partidasDTO);
              partida.getCartoes().add(cartoesModel);
              repository.save(partida);
            });
  }
}
