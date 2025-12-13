package br.com.vemprofut.services.query;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartoesDTO;

public interface ICartoesQueryService {

  void verifyEntitiesExist(CartoesDTO dto);

  CartoesModel verityCartoesExist(Long id);
}
