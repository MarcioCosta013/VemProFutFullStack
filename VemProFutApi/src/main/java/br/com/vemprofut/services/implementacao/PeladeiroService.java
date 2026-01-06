package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.request.UpdatePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.CartoesResumoResponseDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.UpdatePeladeiroResponseDTO;
import br.com.vemprofut.mappers.IHistoricoPeladeiroMapper;
import br.com.vemprofut.mappers.IPeladeiroMapper;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.ICartoesService;
import br.com.vemprofut.services.IHistoricoPeladeiroService;
import br.com.vemprofut.services.IPeladeiroService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j // para gerar logs para verificacao futura de erros
@Service
public class PeladeiroService implements IPeladeiroService {

  @Autowired private IPeladeiroQueryService queryService;

  @Autowired private PeladeiroRepository repository;

  @Autowired private IPeladeiroMapper peladeiroMapper;

  @Autowired private IHistoricoPeladeiroMapper historicoMapper;

  @Autowired private IHistoricoPeladeiroService historicoPeladeiroService;

  @Autowired private ICartoesService cartoesService;

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<SavePeladeiroResponseDTO> create(SavePeladeiroRequestDTO dto) {
    queryService.verifyEmail(dto.email());
    log.info("Email verificado!");
    PeladeiroModel peladeiroModel = peladeiroMapper.saveRequestToModel(dto);
    PeladeiroModel peladeiroSalvo = repository.save(peladeiroModel);

    return historicoPeladeiroService
        .create()
        .thenApply(
            historico -> {
              peladeiroSalvo.setHistoricoPeladeiro(historicoMapper.toModel(historico));
              PeladeiroModel salvo = repository.save(peladeiroSalvo);

              log.info("Peladeiro cadastrado com sucesso!");
              return peladeiroMapper.modelToSaveResponse(salvo);
            });
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<UpdatePeladeiroResponseDTO> update(
      Long id, UpdatePeladeiroRequestDTO dto) {
    log.info("Verificado a existencia de Peladeiro");
    return queryService
        .verifyPeladeiroExist(id)
        .thenApply(
            peladeiroModel -> {
              peladeiroModel.setNome(dto.nome());
              peladeiroModel.setEmail(dto.email());
              peladeiroModel.setApelido(dto.apelido());
              peladeiroModel.setDescricao(dto.descricao());
              peladeiroModel.setWhatsapp(dto.whatsapp());
              peladeiroModel.setPeDominante(dto.peDominante());

              log.info("Peladeiro alterado com sucesso!");

              return peladeiroMapper.modelToUpdateResponse(repository.save(peladeiroModel));
            });
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<PeladeiroDetailResponse> findById(Long id) {
    log.info("Buscando peladeiro pelo id... saida de resposta");
    CompletableFuture<PeladeiroModel> peladeiroFuture = queryService.verifyPeladeiroExist(id);
    CompletableFuture<CartoesResumoResponseDTO> resumoFuture =
        cartoesService.contarCartoesPeladeiro(id);
    return peladeiroFuture.thenCombine(
        resumoFuture,
        (retorno, resumo) ->
            new PeladeiroDetailResponse(
                retorno.getId(),
                retorno.getNome(),
                retorno.getEmail(),
                retorno.getApelido(),
                retorno.getDescricao(),
                retorno.getWhatsapp(),
                retorno.getPeDominante(),
                resumo));
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<PeladeiroModel> findByIdModel(Long id) {
    log.info("Buscando peladeiro pelo id... saida de uso interno");
    return queryService.verifyPeladeiroExist(id);
  }

  //    @Override
  //    @Transactional(readOnly = true)
  //     @Async("defaultExecutor")
  //    public CompletableFuture<List<PeladeiroDTO>> findAll() {
  //
  //        return repository.findAll()
  //                .stream()
  //                .map(peladeiroMapper::toDTO)
  //                .toList();
  //    }

  @Override
  @Async("defaultExecutor")
  @Transactional
  public CompletableFuture<Void> delete(Long id) {
    queryService.verifyPeladeiroExist(id);
    log.info("Existência de Peladeiro confirmada com sucesso!");
    repository.deleteById(id);
    return CompletableFuture.completedFuture(null);
  }

  @Override
  @Async("defaultExecutor")
  @Transactional
  public CompletableFuture<Void> atualizarFoto(Long id, MultipartFile file) {
    queryService.verifyPeladeiroSaveFile(id, file);
    return CompletableFuture.completedFuture(null);
  }
}
