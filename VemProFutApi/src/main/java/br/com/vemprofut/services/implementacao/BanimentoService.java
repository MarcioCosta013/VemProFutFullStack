package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.controllers.request.SaveBanimentoRequestDTO;
import br.com.vemprofut.controllers.response.BanimentoDetailsResponseDTO;
import br.com.vemprofut.controllers.response.SaveBanimentoResponseDTO;
import br.com.vemprofut.mappers.IBanimentoMapper;
import br.com.vemprofut.models.BanimentoModel;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.BanimentoRepository;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.IBanimentoService;
import br.com.vemprofut.services.query.IBanimentoQueryService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BanimentoService implements IBanimentoService {

  @Autowired private BanimentoRepository repository;

  @Autowired private IBanimentoQueryService banimentoQueryService;

  @Autowired private IBanimentoMapper banimentoMapper;

  @Autowired private PeladeiroRepository peladeiroRepository;

  @Autowired private FutRepository futRepository;

  public CompletableFuture<SaveBanimentoResponseDTO> create(SaveBanimentoRequestDTO dto) {
    banimentoQueryService.verifyPeladeiroBanidoExist(dto.fut(), dto.peladeiro());

    // Já foi verificado em FutService se Fut e Peladeiro existem... entao é só buscar
    PeladeiroModel peladeiroModel = peladeiroRepository.getReferenceById(dto.peladeiro());
    FutModel futModel = futRepository.getReferenceById(dto.fut());

    BanimentoModel banimentoModel = new BanimentoModel();
    banimentoModel.addPeladeiro(peladeiroModel);
    banimentoModel.addFut(futModel);
    banimentoModel.setMotivo(dto.motivo());
    banimentoModel.setDataBanimento(dto.dataBaninimento());
    banimentoModel.setDataFimBanimento(dto.dataFimBanimento());

    return CompletableFuture.completedFuture(
        banimentoMapper.toSaveResponse(repository.save(banimentoModel)));
  }

  @Override
  @Async
  public CompletableFuture<List<BanimentoDetailsResponseDTO>> findAll(Long idFut) {
    return CompletableFuture.completedFuture(banimentoQueryService.verifyExistListBanido(idFut));
  }

  @Override
  public CompletableFuture<Void> delete(Long idPeladeiro) {

    repository.deleteById(idPeladeiro);
    return CompletableFuture.completedFuture(null);
  }
}
