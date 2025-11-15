package br.com.vemprofut.services;

import br.com.vemprofut.models.*;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;

import java.util.List;

public interface IPartidasService {

    PartidasDTO create (Boolean jogadoresReservas, FutModel futModel);

    PartidasDTO findById(Long id);

    PartidasModel findByIdModel(Long id);

    void addGols(PeladeiroDTO peladeiroDTO, PartidasDTO partidasDTO);

    void addPeladeiros(Long peladeiroId, PartidasDTO partidasDTO);

    void addCartoes(Long cartaoId, PartidasDTO partidasDTO);

}
