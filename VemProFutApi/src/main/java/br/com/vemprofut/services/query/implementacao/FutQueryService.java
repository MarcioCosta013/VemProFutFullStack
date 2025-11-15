package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.FutInUseException;
import br.com.vemprofut.exceptions.NomeInUseException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.services.query.IFutQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FutQueryService implements IFutQueryService {

  private final FutRepository repository;

  @Override
  public void verifyFutExist(Long dto) {
    if (repository.existsById(dto)) {
      throw new FutInUseException("Fut já cadastrado!");
    }
  }

  @Override
  public FutModel verifyFutExistRetorn(Long fut) {
    return repository
        .findById(fut)
        .orElseThrow(() -> new NotFoundException("Não foi encontrado o Fut de id " + fut));
  }

  @Override
  public void verifyNomeFutExist(String nome) {
    log.info("Iniciando a verificacao do nome...");
    boolean exists = repository.existsByNomeStartingWith(nome);
    if (exists) {
      log.warn("Nome já existente: " + nome);
      throw new NomeInUseException("O nome '" + nome + "' já está cadastrado!");
    }
    log.info("Nome disponível.");
  }

  @Override
  public FutModel verifyNomeFutExistRetorn(String nome) {
    return repository
        .findByNome(nome)
        .orElseThrow(
            () -> new EntityNotFoundException("Futebol com nome '" + nome + "' não encontrado"));
  }
}
