package br.com.vemprofut.services;

import br.com.vemprofut.controllers.request.SavePartidaRequestDTO;
import br.com.vemprofut.controllers.response.SavePartidasResponseDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PartidasModel;
import java.util.concurrent.CompletableFuture;

public interface IPartidasService {

  CompletableFuture<SavePartidasResponseDTO> create(
      SavePartidaRequestDTO requestDTO, FutModel futModel);

  CompletableFuture<PartidasDTO> findById(Long id);

  CompletableFuture<PartidasModel> findByIdModel(Long id);

  CompletableFuture<Void> addGols(PeladeiroDTO peladeiroDTO, PartidasDTO partidasDTO);

  CompletableFuture<Void> addPeladeiros(Long peladeiroId, PartidasDTO partidasDTO);

  CompletableFuture<Void> addCartoes(Long cartaoId, PartidasDTO partidasDTO);
}
