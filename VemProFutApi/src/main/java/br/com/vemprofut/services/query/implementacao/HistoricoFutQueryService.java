package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.HistoricoFutModel;
import br.com.vemprofut.repositories.HistoricoFutRepository;
import br.com.vemprofut.services.query.IHistoricoFutQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoFutQueryService implements IHistoricoFutQueryService {

  @Autowired private HistoricoFutRepository repository;

  @Override
  public HistoricoFutModel verityHistoricoFutExistRetorn(Long id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new NotFoundException("Historico do Fut de ID:" + id + "não encontrado!"));
  }

  @Override
  public void verityHistoricoFutExist(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("HistoricoFUT de id" + id + "não existe!");
    }
  }
}
