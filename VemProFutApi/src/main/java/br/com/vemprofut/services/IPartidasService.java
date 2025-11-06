package br.com.vemprofut.services;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PeladeiroModel;

import java.util.List;

public interface IPartidasService {

    PartidasDTO create (Boolean jogadoresReservas, FutModel futModel);

    PartidasDTO findById(Long id);

    void addGols(PeladeiroDTO peladeiroDTO, PartidasDTO partidasDTO);

    void addPeladeiros(PeladeiroDTO peladeiro, PartidasDTO partidasDTO);

    void addCartoes(CartoesDTO cartoes, PartidasDTO partidasDTO);

}
