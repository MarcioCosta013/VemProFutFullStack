package br.com.vemprofut.services;

import br.com.vemprofut.controllers.request.SaveBanimentoRequestDTO;
import br.com.vemprofut.controllers.response.BanimentoDetailsResponseDTO;
import br.com.vemprofut.controllers.response.SaveBanimentoResponseDTO;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IBanimentoService {
  CompletableFuture<SaveBanimentoResponseDTO> create(SaveBanimentoRequestDTO dto);

  CompletableFuture<List<BanimentoDetailsResponseDTO>> findAll(Long idFut);

  CompletableFuture<Void> delete(Long idPeladeiro);
}
