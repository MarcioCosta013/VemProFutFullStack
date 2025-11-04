package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.CartoesDTO;

import java.util.List;

public interface ICartoesService {

    CartoesDTO create(CartoesDTO dto);

    List<CartoesDTO> findByPeladeiro(Long id);

    List<CartoesDTO> findByPartida(Long id);

    List<CartoesDTO> findByFut(Long id);

    List<CartoesDTO> findAll();

}
