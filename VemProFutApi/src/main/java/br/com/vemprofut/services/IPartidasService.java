package br.com.vemprofut.services;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PeladeiroModel;

import java.util.List;

public interface IPartidasService {

    PartidasDTO create (PartidasDTO dto);

    PartidasDTO findById(Long id);

    void addGolPartida(GolsPartidaModel gols);

    void addPeladeiros(PeladeiroModel peladeiro);

    void addCartoes(CartoesModel cartoes);

    List<PartidasDTO> findAll();

    PartidasDTO update(Long id, PartidasDTO dto);
}
