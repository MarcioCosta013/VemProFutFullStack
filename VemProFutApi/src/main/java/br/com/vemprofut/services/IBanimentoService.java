package br.com.vemprofut.services;

import br.com.vemprofut.controllers.request.SaveBanimentoRequestDTO;
import br.com.vemprofut.controllers.response.BanimentoDetailsResponseDTO;
import br.com.vemprofut.controllers.response.SaveBanimentoResponseDTO;
import java.util.List;

public interface IBanimentoService {
  SaveBanimentoResponseDTO create(SaveBanimentoRequestDTO dto);

  List<BanimentoDetailsResponseDTO> findAll(Long idFut);

  void delete(Long idPeladeiro);
}
