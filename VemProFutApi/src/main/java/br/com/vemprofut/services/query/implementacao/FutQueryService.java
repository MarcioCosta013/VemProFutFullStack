package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.*;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.IUploadLocalService;
import br.com.vemprofut.services.query.IFutQueryService;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@AllArgsConstructor
public class FutQueryService implements IFutQueryService {

  private final FutRepository repository;
  private final IUploadLocalService uploadLocalService;
  private final PeladeiroRepository peladeiroRepository;

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

  @Override
  public void verifyFutSaveFile(Long id, MultipartFile file) {
    FutModel futModel = verifyFutExistRetorn(id);
    try {
      String url = uploadLocalService.upload(file, "fut");
      futModel.setFoto_url(url);
      repository.save(futModel);
    } catch (IOException ex) {
      throw new FileStorageException(
          "Erro ao salvar a foto do peladeiro com id: " + id, ex.getCause());
    }
  }

  @Override
  public void verityPeladeiroInList(FutModel futModel, PeladeiroModel peladeiroModel) {
    if (futModel.getPeladeiros().contains(peladeiroModel)) {
      throw new PeladeiroNotExistException("Peladeiro já cadastrado na lista de peladeiros!");
    }
  }

  @Override
  public void verifyBanidoListPeladeiros(FutModel futModel, PeladeiroModel peladeiroModel) {
    if (!(futModel.getPeladeiros().contains(peladeiroModel))) {
      throw new NotFoundException("Nao consta esse Peladeiro na lista de Peladeiros");
    }
  }
}
