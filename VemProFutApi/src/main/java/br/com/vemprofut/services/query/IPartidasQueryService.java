package br.com.vemprofut.services.query;

import br.com.vemprofut.models.PartidasModel;

public interface IPartidasQueryService {

  PartidasModel verifyPartidaExistWithRetorn(Long id);
}
