package br.com.vemprofut.services;

import br.com.vemprofut.controllers.request.AddPeladeiroInFutListRequestDTO;
import br.com.vemprofut.controllers.request.SaveFutRequestDTO;
import br.com.vemprofut.controllers.request.SavePartidaRequestDTO;
import br.com.vemprofut.controllers.request.UpdateFutRequestDTO;
import br.com.vemprofut.controllers.response.*;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import java.util.List;

public interface IFutService {

  SaveFutResponseDTO create(SaveFutRequestDTO dto);

  FutDetailsResponse findById(Long id);

  FutModel findByIdModel(Long id);

  FutDTO findByNome(String nome);

  List<FutDTO> findAll();

  UpdateFutResponseDTO update(Long id, UpdateFutRequestDTO dto);

  void delete(Long id);

  SavePartidasResponseDTO criarPartida(SavePartidaRequestDTO requestDTO, FutModel futModel);

  void addPeladeiro(AddPeladeiroInFutListRequestDTO requestDTO);

  List<SavePartidasResponseDTO> criarPartidasList(List<SavePartidaRequestDTO> requestDTOS);

  List<PeladeiroResponseDTO> listarPeladeiroCadastradosFut(Long futId);
}
