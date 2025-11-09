package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.GolsPartidaDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.GolsPartidaModel;

import java.util.List;
import java.util.Optional;

public interface IGolsPartidaService {

    GolsPartidaModel create(PeladeiroDTO peladeiroDTO, PartidasDTO partidasDTO);

    GolsPartidaDTO findById(Long id);

    GolsPartidaModel findByIdModel(Long id);

    List<GolsPartidaDTO> findAll();

    List<GolsPartidaDTO> findAllPeladeiro( Long id);

    List<GolsPartidaDTO> findAllPartida(Long id);

    void delete(Long id);
}
