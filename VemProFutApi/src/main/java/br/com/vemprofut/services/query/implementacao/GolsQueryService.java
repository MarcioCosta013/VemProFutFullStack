package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.repositories.GolsPartidaRepository;
import br.com.vemprofut.services.query.IGolsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GolsQueryService implements IGolsQueryService {
  @Autowired GolsPartidaRepository repository;

  @Override
  public GolsPartidaModel verifyGolExistWithRetorn(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("id Gol: " + id + " não encontrado!"));
  }

  @Override
  public void verifyGolExist(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("Identificador de Gol" + id + " não encontrado");
    }
  }
}
