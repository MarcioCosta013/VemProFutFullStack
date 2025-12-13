package br.com.vemprofut.services.query;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;

public interface ICartoesQueryService {

  void verifyEntitiesExist(CartoesDTO dto);

  CartoesModel verityCartoesExist(Long id);
}
