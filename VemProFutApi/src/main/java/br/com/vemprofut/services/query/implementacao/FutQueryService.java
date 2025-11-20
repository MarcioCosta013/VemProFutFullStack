package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.FutInUseException;
import br.com.vemprofut.exceptions.NomeInUseException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.services.query.IFutQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  public FutModel verifyFutExistRetornListPeladeiro(Long id) {
    return repository
        .buscarFutComListPeladeiros(id)
        .orElseThrow(
            () -> new NotFoundException("Não foi encontrado o Fut de id ou lista de Peladeiro"));
  }

  @Override
  public FutModel verifyFutExistRetornListEditores(Long id) {
    return repository
        .buscarFutComListEditores(id)
        .orElseThrow(
            () -> new NotFoundException("Não foi encontrado o Fut de id ou lista de Editores"));
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

  @Override
  public void verifyPeladeiroExistInListOrAdm(FutModel futModel, PeladeiroModel model) {
    if (!(futModel.getPeladeiros().contains(model))) {
      throw new NotFoundException("Editor nao cadastrado na lista de peladeiros do fut!");
    }
    if (futModel.getAdministradorPeladeiro().equals(model)) {
      throw new NotFoundException("O Editor selecionado já é o Administrador do Fut!");
    }
  }
}
