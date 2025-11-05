package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.GolsPartidaDTO;

import java.util.List;
import java.util.Optional;

public interface IGolsPartidaService {

    void create(GolsPartidaDTO dto);

    GolsPartidaDTO findById(Long id);

    List<GolsPartidaDTO> findAll();

    List<GolsPartidaDTO> findAllPeladeiro( Long id);

    List<GolsPartidaDTO> findAllPartida(Long id);

    void delete(Long id);
}
