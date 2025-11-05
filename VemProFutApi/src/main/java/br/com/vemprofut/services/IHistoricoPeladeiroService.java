package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;

import java.util.List;

public interface IHistoricoPeladeiroService {

    HistoricoPeladeiroDTO create();

    HistoricoPeladeiroDTO findById(Long id);


    HistoricoPeladeiroDTO update(Long id, HistoricoPeladeiroDTO dto);

    void delete(Long id);
}
