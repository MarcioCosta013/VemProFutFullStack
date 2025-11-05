package br.com.vemprofut.services.query;

import br.com.vemprofut.models.HistoricoPeladeiroModel;

public interface IHistoricoPeladeiroQueryService {

    HistoricoPeladeiroModel verityHistoricoPeladeiroExistReturn(Long id);

    void verityHistoricoPeladeiroExist(Long id);
}
