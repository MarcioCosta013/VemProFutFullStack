package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.PeladeiroDTO;

import java.util.List;

public interface IPeladeiroService {

    PeladeiroDTO create(PeladeiroDTO dto);

    PeladeiroDTO update(Long id, PeladeiroDTO dto);

    PeladeiroDTO findById(Long id);

    List<PeladeiroDTO> findAll();

    void delete(Long id);
}
