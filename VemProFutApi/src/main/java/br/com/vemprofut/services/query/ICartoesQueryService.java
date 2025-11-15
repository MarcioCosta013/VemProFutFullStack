package br.com.vemprofut.services.query;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;

public interface ICartoesQueryService {

    void verifyPeladeiroExist(PeladeiroDTO peladeiroDTO);

    void verifyPartidasExist(PartidasDTO partidasDTO);

    void verifyEntitiesExist(CartoesDTO dto);

    CartoesModel verityCartoesExist (Long id);
}
