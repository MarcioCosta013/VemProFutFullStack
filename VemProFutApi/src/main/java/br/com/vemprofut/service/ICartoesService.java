package br.com.vemprofut.service;

import br.com.vemprofut.DTOs.CartoesDTO;
import br.com.vemprofut.DTOs.FutDTO;
import br.com.vemprofut.DTOs.PeladeiroDTO;

import java.util.List;

public interface ICartoesService {

    CartoesDTO criar(CartoesDTO dto);

    List<CartoesDTO> buscarCartoesPeladeiro (PeladeiroDTO peladeiro);

    List<CartoesDTO> buscarCartoesFut (FutDTO fut);
}
