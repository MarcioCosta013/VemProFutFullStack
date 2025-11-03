package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.PartidasDTO;

import java.util.List;

public interface IPartidasService {

    PartidasDTO create (PartidasDTO dto);

    PartidasDTO findById(Long id);

    List<PartidasDTO> findAll();

    PartidasDTO update(Long id, PartidasDTO dto);
}
