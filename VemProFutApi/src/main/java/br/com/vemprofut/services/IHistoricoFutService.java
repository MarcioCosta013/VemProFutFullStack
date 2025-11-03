package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.HistoricoFutDTO;

import java.util.List;

public interface IHistoricoFutService {

    HistoricoFutDTO create(HistoricoFutDTO dto);

    HistoricoFutDTO findById(Long id);

    List<HistoricoFutDTO> findAll();

    HistoricoFutDTO update(Long id, HistoricoFutDTO dto);

    void delete(Long id);
}
