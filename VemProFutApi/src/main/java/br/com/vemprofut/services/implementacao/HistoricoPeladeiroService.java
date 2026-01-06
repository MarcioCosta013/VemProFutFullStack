package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.IHistoricoPeladeiroMapper;
import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import br.com.vemprofut.repositories.HistoricoPeladeiroRepository;
import br.com.vemprofut.services.IHistoricoPeladeiroService;
import br.com.vemprofut.services.query.IHistoricoPeladeiroQueryService;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoricoPeladeiroService implements IHistoricoPeladeiroService {

  @Autowired private IHistoricoPeladeiroQueryService queryService;

  @Autowired private IHistoricoPeladeiroMapper mapper;

  @Autowired private HistoricoPeladeiroRepository repository;

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<HistoricoPeladeiroDTO> create() {
    HistoricoPeladeiroModel historico = new HistoricoPeladeiroModel();
    var response = mapper.toDTO(repository.save(historico));
    return CompletableFuture.completedFuture(response);

    // Se mudar futuramente repository para async tem que compor o metodo com thenApply.
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<HistoricoPeladeiroDTO> findById(Long id) {

    return queryService.verityHistoricoPeladeiroExistReturn(id).thenApply(mapper::toDTO);
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<HistoricoPeladeiroModel> findByIdModel(Long id) {

    return queryService.verityHistoricoPeladeiroExistReturn(id);
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<HistoricoPeladeiroDTO> update(Long id, HistoricoPeladeiroDTO dto) {
    return queryService
        .verityHistoricoPeladeiroExistReturn(id)
        .thenApply(
            historicoPeladeiroModel -> {
              historicoPeladeiroModel.setGolsDoPeladeiro(dto.golsDoPeladeiro());
              historicoPeladeiroModel.setNotaPeladeiro(dto.notaPeladeiro());
              historicoPeladeiroModel.setPartidasJogadas(dto.partidasJogadas());
              historicoPeladeiroModel.setPartidasGanhas(dto.partidasGanhas());

              return mapper.toDTO(repository.save(historicoPeladeiroModel));
            });
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> delete(Long id) {
    queryService.verityHistoricoPeladeiroExist(id);
    repository.deleteById(id);
    return CompletableFuture.completedFuture(null);
  }

  // TODO: implementar a somatoria de gols ao historico peladeiro
  // TODO: implementar a somatoria das notas do peladeiro, e como vai ser a pontuacao
  // TODO: implementar a somatoria de partidas jogadas
  // TODO: implementar a somatoria de partidas ganhas
}
