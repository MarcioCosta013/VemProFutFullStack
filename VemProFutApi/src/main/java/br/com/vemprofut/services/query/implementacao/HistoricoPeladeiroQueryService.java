package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import br.com.vemprofut.repositories.HistoricoPeladeiroRepository;
import br.com.vemprofut.services.query.IHistoricoPeladeiroQueryService;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HistoricoPeladeiroQueryService implements IHistoricoPeladeiroQueryService {
  @Autowired HistoricoPeladeiroRepository repository;

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<HistoricoPeladeiroModel> verityHistoricoPeladeiroExistReturn(Long id) {
    return repository
        .findById(id)
        .map(CompletableFuture::completedFuture)
        .orElseGet(
            () ->
                CompletableFuture.failedFuture(
                    new NotFoundException(
                        "Não foi encontrado o Historico de Peladeiro de id " + id)));
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> verityHistoricoPeladeiroExist(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("Id: " + id + "de Historico Jogador não encontrado!");
    }
    return CompletableFuture.completedFuture(null);
  }
}
