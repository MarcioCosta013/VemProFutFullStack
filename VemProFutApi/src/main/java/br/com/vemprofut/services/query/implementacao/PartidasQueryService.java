package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.repositories.CartoesRepository;
import br.com.vemprofut.repositories.GolsPartidaRepository;
import br.com.vemprofut.repositories.PartidasRepository;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.query.IPartidasQueryService;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PartidasQueryService implements IPartidasQueryService {
  @Autowired private PartidasRepository repository;

  @Autowired private GolsPartidaRepository golsRepository;

  @Autowired private CartoesRepository cartoesRepository;

  @Autowired private PeladeiroRepository peladeiroRepository;

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<PartidasModel> verifyPartidaExistWithRetorn(Long id) {
    return repository
        .findById(id)
        .map(CompletableFuture::completedFuture)
        .orElseGet(
            () ->
                CompletableFuture.failedFuture(
                    new NotFoundException("Não foi encontrado a Partida de id " + id)));
  }
}
