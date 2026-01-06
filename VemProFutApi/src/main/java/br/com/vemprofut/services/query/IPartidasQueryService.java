package br.com.vemprofut.services.query;

import br.com.vemprofut.models.PartidasModel;
import java.util.concurrent.CompletableFuture;

public interface IPartidasQueryService {

  CompletableFuture<PartidasModel> verifyPartidaExistWithRetorn(Long id);
}
