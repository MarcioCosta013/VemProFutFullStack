package br.com.vemprofut.services.query;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;

public interface IPartidasQueryService {

    PartidasModel verifyPartidaExistWithRetorn(Long id);

}
