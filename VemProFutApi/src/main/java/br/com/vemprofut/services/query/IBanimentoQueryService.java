package br.com.vemprofut.services.query;

import br.com.vemprofut.controllers.response.BanimentoDetailsResponseDTO;
import java.util.List;

public interface IBanimentoQueryService {
  void verifyPeladeiroBanidoExist(Long idFut, Long idPeladeiro);

  List<BanimentoDetailsResponseDTO> verifyExistListBanido(Long idFut);
}
