package br.com.vemprofut.services.query;

import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.multipart.MultipartFile;

public interface IFutQueryService {

  CompletableFuture<Void> verifyFutExist(Long dto);

  CompletableFuture<FutModel> verifyFutExistRetorn(Long fut);

  CompletableFuture<FutModel> verifyFutExistRetornListPeladeiro(Long id);

  CompletableFuture<FutModel> verifyFutExistRetornListEditores(Long id);

  CompletableFuture<Void> verifyNomeFutExist(String nome);

  CompletableFuture<FutModel> verifyNomeFutExistRetorn(String nome);

  CompletableFuture<Void> verifyPeladeiroExistInListOrAdm(FutModel futModel, PeladeiroModel model);

  CompletableFuture<Void> verifyFutSaveFile(Long id, MultipartFile file);

  CompletableFuture<Void> verityPeladeiroInList(FutModel futModel, PeladeiroModel peladeiroModel);

  CompletableFuture<Void> verifyBanidoListPeladeiros(
      FutModel futModel, PeladeiroModel peladeiroModel);
}
