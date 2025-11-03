package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.GolsPartidaDTO;

import java.util.List;

public interface IGolsPartidaService {

    GolsPartidaDTO create(GolsPartidaDTO dto);

    GolsPartidaDTO findById(Long id);

    List<GolsPartidaDTO> findAll();

    GolsPartidaDTO update(Long id, GolsPartidaDTO dto);

    void delete(Long id);
}
