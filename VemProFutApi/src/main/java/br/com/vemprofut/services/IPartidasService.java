package br.com.vemprofut.services;

import br.com.vemprofut.controllers.request.SavePartidaRequestDTO;
import br.com.vemprofut.controllers.response.SavePartidasResponseDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PartidasModel;

public interface IPartidasService {

  SavePartidasResponseDTO create(SavePartidaRequestDTO requestDTO, FutModel futModel);

  PartidasDTO findById(Long id);

  PartidasModel findByIdModel(Long id);

  void addGols(PeladeiroDTO peladeiroDTO, PartidasDTO partidasDTO);

  void addPeladeiros(Long peladeiroId, PartidasDTO partidasDTO);

  void addCartoes(Long cartaoId, PartidasDTO partidasDTO);
}
