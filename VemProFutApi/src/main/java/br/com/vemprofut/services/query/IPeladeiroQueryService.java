package br.com.vemprofut.services.query;

import br.com.vemprofut.models.PeladeiroModel;
import org.springframework.web.multipart.MultipartFile;

public interface IPeladeiroQueryService {

  void verifyEmail(final String email);

  PeladeiroModel verifyPeladeiroExist(final Long id);

  void verifyPeladeiroSaveFile(Long id, MultipartFile file);
}
