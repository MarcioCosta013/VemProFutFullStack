package br.com.vemprofut.services;

import br.com.vemprofut.controllers.request.*;
import br.com.vemprofut.controllers.response.*;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.multipart.MultipartFile;

public interface IFutService {

  CompletableFuture<SaveFutResponseDTO> create(SaveFutRequestDTO dto);

  CompletableFuture<FutDetailsResponse> findById(Long id);

  CompletableFuture<FutModel> findByIdModel(Long id);

  CompletableFuture<FutDTO> findByNome(String nome);

  CompletableFuture<List<FutDTO>> findAll();

  CompletableFuture<UpdateFutResponseDTO> update(Long id, UpdateFutRequestDTO dto);

  CompletableFuture<Void> delete(Long id);

  CompletableFuture<SavePartidasResponseDTO> criarPartida(
      SavePartidaRequestDTO requestDTO, FutModel futModel);

  CompletableFuture<Void> addPeladeiro(AddPeladeiroInFutListRequestDTO requestDTO);

  CompletableFuture<List<SavePartidasResponseDTO>> criarPartidasList(
      List<SavePartidaRequestDTO> requestDTOS);

  CompletableFuture<List<PeladeiroResponseDTO>> listarPeladeiroCadastradosFut(Long futId);

  CompletableFuture<Void> addEditor(AddEditorInFutListResquestDTO resquestDTO);

  CompletableFuture<List<PeladeiroNameIdResponseDTO>> listarEditoresCadastradosFut(Long idFut);

  CompletableFuture<Void> atualizarFotoCapa(Long id, MultipartFile file);

  CompletableFuture<SaveBanimentoResponseDTO> addBanimentoList(SaveBanimentoRequestDTO dto);

  CompletableFuture<List<BanimentoDetailsResponseDTO>> findAllBanidos(Long idFut);

  CompletableFuture<Void> removeBanido(Long idPeladeiro, Long idFut);
}
