package br.com.vemprofut.services;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.request.UpdatePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.UpdatePeladeiroResponseDTO;
import br.com.vemprofut.models.PeladeiroModel;
import org.springframework.web.multipart.MultipartFile;

public interface IPeladeiroService {

  SavePeladeiroResponseDTO create(SavePeladeiroRequestDTO dto);

  UpdatePeladeiroResponseDTO update(Long id, UpdatePeladeiroRequestDTO dto);

  PeladeiroDetailResponse findById(Long id);

  PeladeiroModel findByIdModel(Long id);

  // List<PeladeiroDTO> findAll();

  void delete(Long id);

  void atualizarFoto(Long id, MultipartFile file);
}
