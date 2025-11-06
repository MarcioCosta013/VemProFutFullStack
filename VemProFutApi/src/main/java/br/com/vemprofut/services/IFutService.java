package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.FutModel;

import java.util.List;

public interface IFutService {

    FutDTO create(FutDTO dto);

    FutDTO findById(Long id);

    FutDTO findByNome(String nome);

    List<FutDTO> findAll();

    FutDTO update(Long id, FutDTO dto);

    void delete(Long id);

    void criarPartida(Boolean jogadoresReservas, FutModel futModel);

    void addPeladeiro(FutDTO dto, PeladeiroDTO peladeiroDTO);

    void addCartoes(FutDTO dto, CartoesDTO cartoesDTO);
}
