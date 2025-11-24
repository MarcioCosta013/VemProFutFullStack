package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.EmailInUseException;
import br.com.vemprofut.exceptions.FileStorageException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.IUploadLocalService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j // para gerar logs para verificacao futura de erros
@Service
@AllArgsConstructor
public class PeladeiroQueryService implements IPeladeiroQueryService {

  private final PeladeiroRepository repository;
  private final IUploadLocalService uploadLocalService;

  @Override
  public void verifyEmail(String email) {
    if (repository.existsByEmail(email)) {
      var message = "O e-mail " + email + " já está em uso";
      log.error(message);
      throw new EmailInUseException(message);
    }
  }

  @Override
  public PeladeiroModel verifyPeladeiroExist(Long id) {
    log.debug("Verificando existência do Peladeiro com ID: {}", id);

    PeladeiroModel peladeiro =
        repository
            .findById(id)
            .orElseThrow(
                () -> {
                  log.warn("Peladeiro com ID {} não encontrado no banco de dados", id);
                  return new NotFoundException("Não foi encontrado o Peladeiro de id " + id);
                });

    log.debug("Peladeiro encontrado: {}", peladeiro);
    return peladeiro;
  }

  @Override
  public void verifyPeladeiroSaveFile(Long id, MultipartFile file) {
    PeladeiroModel peladeiroModel = verifyPeladeiroExist(id);
    try {
      String url = uploadLocalService.upload(file, "peladeiro");
      peladeiroModel.setFotoUrl(url);
      repository.save(peladeiroModel);
      log.info("Foto salva!");
    } catch (IOException ex) {
      throw new FileStorageException(
          "Erro ao salvar a foto do peladeiro com id: " + id, ex.getCause());
    }
  }
}
