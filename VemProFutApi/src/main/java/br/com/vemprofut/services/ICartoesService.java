package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.CartoesDTO;

import java.util.List;

public interface ICartoesService {

    CartoesDTO create(CartoesDTO cartoesDTO);

    CartoesDTO findById(Long id);

    List<CartoesDTO> findAll();

    CartoesDTO update(Long id, CartoesDTO cartoesDTO);
}
