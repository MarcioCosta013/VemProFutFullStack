package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;

import java.util.List;

public interface IHistoricoPeladeiroService {

    HistoricoPeladeiroDTO create();

    HistoricoPeladeiroDTO findById(Long id);

    HistoricoPeladeiroModel findByIdModel(Long id);

    HistoricoPeladeiroDTO update(Long id, HistoricoPeladeiroDTO dto);

    void delete(Long id);
}
