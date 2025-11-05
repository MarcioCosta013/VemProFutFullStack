package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.FutDTO;

import java.util.List;

public interface IFutService {

    FutDTO create(FutDTO dto);

    FutDTO findById(Long id);

    FutDTO findByNome(String nome);

    List<FutDTO> findAll();

    FutDTO update(Long id, FutDTO dto);

    void delete(Long id);
}
