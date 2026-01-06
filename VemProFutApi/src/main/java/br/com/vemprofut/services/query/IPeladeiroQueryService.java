package br.com.vemprofut.services.query;

import br.com.vemprofut.models.PeladeiroModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.multipart.MultipartFile;

public interface IPeladeiroQueryService {

  CompletableFuture<Void> verifyEmail(final String email);

  CompletableFuture<PeladeiroModel> verifyPeladeiroExist(final Long id);

  CompletableFuture<Void> verifyPeladeiroSaveFile(Long id, MultipartFile file);
}
