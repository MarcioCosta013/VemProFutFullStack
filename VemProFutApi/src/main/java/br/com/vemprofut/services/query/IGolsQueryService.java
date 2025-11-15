package br.com.vemprofut.services.query;

import br.com.vemprofut.models.GolsPartidaModel;

public interface IGolsQueryService {

    GolsPartidaModel verifyGolExistWithRetorn(Long id);

    void verifyGolExist(Long id);
}
