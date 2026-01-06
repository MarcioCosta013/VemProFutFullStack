package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.EmailInUseException;
import br.com.vemprofut.exceptions.FileStorageException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.IUploadLocalService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j // para gerar logs para verificacao futura de erros
@Service
@AllArgsConstructor
public class PeladeiroQueryService implements IPeladeiroQueryService {

  private final PeladeiroRepository repository;
  private final IUploadLocalService uploadLocalService;

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> verifyEmail(String email) {
    if (repository.existsByEmail(email)) {
      var message = "O e-mail " + email + " já está em uso";
      log.error(message);
      throw new EmailInUseException(message);
    }
    return CompletableFuture.completedFuture(null);
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<PeladeiroModel> verifyPeladeiroExist(Long id) {
    log.debug("Verificando existência do Peladeiro com ID: {}", id);

    return repository
        .findById(id)
        .map(CompletableFuture::completedFuture)
        .orElseGet(
            () ->
                CompletableFuture.failedFuture(
                    new NotFoundException("Não foi encontrado o Peladeiro de id " + id)));
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> verifyPeladeiroSaveFile(Long id, MultipartFile file) {
    return verifyPeladeiroExist(id) // retorna CompletableFuture<PeladeiroModel>
        .thenAccept(
            peladeiroModel -> {
              try {
                String url = uploadLocalService.upload(file, "peladeiro");
                peladeiroModel.setFotoUrl(url);
                repository.save(peladeiroModel);
                log.info("Foto salva!");
              } catch (IOException ex) {
                throw new FileStorageException(
                    "Erro ao salvar a foto do peladeiro com id: " + id, ex);
              }
            });
  }
}
