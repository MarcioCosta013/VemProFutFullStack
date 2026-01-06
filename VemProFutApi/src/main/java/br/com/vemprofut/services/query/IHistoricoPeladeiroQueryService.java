package br.com.vemprofut.services.query;

import br.com.vemprofut.models.HistoricoPeladeiroModel;
import java.util.concurrent.CompletableFuture;

public interface IHistoricoPeladeiroQueryService {

  CompletableFuture<HistoricoPeladeiroModel> verityHistoricoPeladeiroExistReturn(Long id);

  CompletableFuture<Void> verityHistoricoPeladeiroExist(Long id);
}
