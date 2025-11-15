package br.com.vemprofut.services;

import br.com.vemprofut.controllers.response.CartoesResumoResponseDTO;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import java.util.List;

public interface ICartoesService {

  CartoesDTO create(CartoesDTO dto);

  List<CartoesDTO> findByPeladeiro(Long id);

  List<CartoesDTO> findByPartida(Long id);

  List<CartoesDTO> findByFut(Long id);

  List<CartoesDTO> findAll();

  CartoesDTO findById(Long id);

  CartoesModel findByIdModel(Long id);

  CartoesResumoResponseDTO contarCartoesPeladeiro(Long peladeiroId);

  CartoesResumoResponseDTO contarCartoesFut(Long futId);
}
