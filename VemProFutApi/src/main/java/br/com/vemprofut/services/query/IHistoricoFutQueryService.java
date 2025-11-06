package br.com.vemprofut.services.query;

import br.com.vemprofut.models.HistoricoFutModel;

public interface IHistoricoFutQueryService {

    HistoricoFutModel verityHistoricoFutExistRetorn(Long id);

    void verityHistoricoFutExist(Long id);
}
