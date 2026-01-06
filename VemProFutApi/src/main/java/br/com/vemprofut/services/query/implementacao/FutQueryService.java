package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.*;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.services.IUploadLocalService;
import br.com.vemprofut.services.query.IFutQueryService;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@AllArgsConstructor
public class FutQueryService implements IFutQueryService {

  private final FutRepository repository;
  private final IUploadLocalService uploadLocalService;

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> verifyFutExist(Long dto) {
    if (repository.existsById(dto)) {
      return CompletableFuture.failedFuture(new FutInUseException("Fut já cadastrado!"));
    }
    return CompletableFuture.completedFuture(null);
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<FutModel> verifyFutExistRetorn(Long fut) {
    return repository
        .findById(fut)
        .map(CompletableFuture::completedFuture)
        .orElseGet(
            () ->
                CompletableFuture.failedFuture(
                    new NotFoundException("Não foi encontrado o Fut de id " + fut)));
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<FutModel> verifyFutExistRetornListPeladeiro(Long id) {
    return repository
        .buscarFutComListPeladeiros(id)
        .map(CompletableFuture::completedFuture)
        .orElseGet(
            () ->
                CompletableFuture.failedFuture(
                    new NotFoundException("Não foi encontrado o Fut de id ou lista de Peladeiro")));
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<FutModel> verifyFutExistRetornListEditores(Long id) {
    return repository
        .buscarFutComListEditores(id)
        .map(CompletableFuture::completedFuture)
        .orElseGet(
            () ->
                CompletableFuture.failedFuture(
                    new NotFoundException("Não foi encontrado o Fut de id ou lista de Editores")));
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> verifyNomeFutExist(String nome) {
    log.info("Iniciando a verificacao do nome...");
    if (repository.existsByNomeStartingWith(nome)) {
      log.warn("Nome já existente: " + nome);
      return CompletableFuture.failedFuture(
          new NomeInUseException("O nome '" + nome + "' já está cadastrado!"));
    }
    log.info("Nome disponível.");
    return CompletableFuture.completedFuture(null);
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<FutModel> verifyNomeFutExistRetorn(String nome) {
    return repository
        .findByNome(nome)
        .map(CompletableFuture::completedFuture)
        .orElseGet(
            () ->
                CompletableFuture.failedFuture(
                    new EntityNotFoundException("Futebol com nome '" + nome + "' não encontrado")));
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> verifyPeladeiroExistInListOrAdm(
      FutModel futModel, PeladeiroModel model) {
    if (!(futModel.getPeladeiros().contains(model))) {
      return CompletableFuture.failedFuture(
          new NotFoundException("Editor nao cadastrado na lista de peladeiros do fut!"));
    }
    if (futModel.getAdministradorPeladeiro().equals(model)) {
      return CompletableFuture.failedFuture(
          new NotFoundException("O Editor selecionado já é o Administrador do Fut!"));
    }
    return CompletableFuture.completedFuture(null);
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> verifyFutSaveFile(Long id, MultipartFile file) {
    return verifyFutExistRetorn(id)
        .thenAccept(
            futModel -> {
              try {
                String url = uploadLocalService.upload(file, "fut");
                futModel.setFoto_url(url);
                repository.save(futModel);
              } catch (IOException ex) {
                throw new FileStorageException(
                    "Erro ao salvar a foto do peladeiro com id: " + id, ex.getCause());
              }
            });
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> verityPeladeiroInList(
      FutModel futModel, PeladeiroModel peladeiroModel) {
    if (futModel.getPeladeiros().contains(peladeiroModel)) {
      return CompletableFuture.failedFuture(
          new PeladeiroNotExistException("Peladeiro já cadastrado na lista de peladeiros!"));
    }
    return CompletableFuture.completedFuture(null);
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> verifyBanidoListPeladeiros(
      FutModel futModel, PeladeiroModel peladeiroModel) {
    if (!(futModel.getPeladeiros().contains(peladeiroModel))) {
      return CompletableFuture.failedFuture(
          new NotFoundException("Nao consta esse Peladeiro na lista de Banidos"));
    }
    return CompletableFuture.completedFuture(null);
  }
}
