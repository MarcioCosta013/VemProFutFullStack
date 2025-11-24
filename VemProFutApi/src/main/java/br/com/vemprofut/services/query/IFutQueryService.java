package br.com.vemprofut.services.query;

import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import org.springframework.web.multipart.MultipartFile;

public interface IFutQueryService {

  void verifyFutExist(Long dto);

  FutModel verifyFutExistRetorn(Long fut);

  FutModel verifyFutExistRetornListPeladeiro(Long id);

  FutModel verifyFutExistRetornListEditores(Long id);

  void verifyNomeFutExist(String nome);

  FutModel verifyNomeFutExistRetorn(String nome);

  void verifyPeladeiroExistInListOrAdm(FutModel futModel, PeladeiroModel model);

  void verifyFutSaveFile(Long id, MultipartFile file);
}
