package br.com.vemprofut.service;

import br.com.vemprofut.DTOs.CartoesDTO;
import br.com.vemprofut.DTOs.FutDTO;
import br.com.vemprofut.DTOs.PeladeiroDTO;

import java.util.List;

public interface ICartoesService {

    CartoesDTO create(CartoesDTO cartoesDTO);

    CartoesDTO findById(Long id);

    List<CartoesDTO> findAll();

    CartoesDTO update(Long id, CartoesDTO cartoesDTO);
}
