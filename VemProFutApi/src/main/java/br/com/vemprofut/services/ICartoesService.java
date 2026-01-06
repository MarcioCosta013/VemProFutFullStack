package br.com.vemprofut.services;

import br.com.vemprofut.controllers.response.CartoesResumoResponseDTO;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICartoesService {

  CompletableFuture<CartoesDTO> create(CartoesDTO dto);

  CompletableFuture<List<CartoesDTO>> findByPeladeiro(Long id);

  CompletableFuture<List<CartoesDTO>> findByPartida(Long id);

  CompletableFuture<List<CartoesDTO>> findByFut(Long id);

  CompletableFuture<List<CartoesDTO>> findAll();

  CompletableFuture<CartoesDTO> findById(Long id);

  CompletableFuture<CartoesModel> findByIdModel(Long id);

  CompletableFuture<CartoesResumoResponseDTO> contarCartoesPeladeiro(Long peladeiroId);

  CompletableFuture<CartoesResumoResponseDTO> contarCartoesFut(Long futId);
}
