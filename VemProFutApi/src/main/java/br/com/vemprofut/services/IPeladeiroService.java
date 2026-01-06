package br.com.vemprofut.services;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.request.UpdatePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.UpdatePeladeiroResponseDTO;
import br.com.vemprofut.models.PeladeiroModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.multipart.MultipartFile;

public interface IPeladeiroService {

  CompletableFuture<SavePeladeiroResponseDTO> create(SavePeladeiroRequestDTO dto);

  CompletableFuture<UpdatePeladeiroResponseDTO> update(Long id, UpdatePeladeiroRequestDTO dto);

  CompletableFuture<PeladeiroDetailResponse> findById(Long id);

  CompletableFuture<PeladeiroModel> findByIdModel(Long id);

  // CompletableFuture<List<PeladeiroDTO>> findAll();

  CompletableFuture<Void> delete(Long id);

  CompletableFuture<Void> atualizarFoto(Long id, MultipartFile file);
}
