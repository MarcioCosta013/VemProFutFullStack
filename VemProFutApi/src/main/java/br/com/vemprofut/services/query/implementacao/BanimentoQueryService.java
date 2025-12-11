package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.controllers.response.BanimentoDetailsResponseDTO;
import br.com.vemprofut.exceptions.BanimentoExistException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.mappers.IBanimentoMapper;
import br.com.vemprofut.models.BanimentoModel;
import br.com.vemprofut.repositories.BanimentoRepository;
import br.com.vemprofut.services.query.IBanimentoQueryService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BanimentoQueryService implements IBanimentoQueryService {

  private BanimentoRepository repository;

  private IBanimentoMapper mapper;

  @Override
  public void verifyPeladeiroBanidoExist(Long idFut, Long idPeladeiro) {
    if (repository.buscarBanimentoFutPeladeiro(idFut, idPeladeiro).isPresent()) {
      throw new BanimentoExistException("Banimento já registrado!");
    }
  }

  @Override
  public List<BanimentoDetailsResponseDTO> verifyExistListBanido(Long idFut) {
    List<BanimentoModel> lista = repository.buscarListBanidos(idFut);

    if (lista.isEmpty()) {
      throw new NotFoundException("Não foi encontrado banidos!");
    }

    List<BanimentoDetailsResponseDTO> listRetorn = mapper.toDetailsList(lista);

    return listRetorn;
  }
}
