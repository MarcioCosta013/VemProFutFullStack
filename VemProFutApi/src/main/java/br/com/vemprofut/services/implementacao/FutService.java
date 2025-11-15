package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.controllers.request.SaveFutRequestDTO;
import br.com.vemprofut.controllers.request.UpdateFutRequestDTO;
import br.com.vemprofut.controllers.response.FutDetailsResponse;
import br.com.vemprofut.controllers.response.SaveFutResponseDTO;
import br.com.vemprofut.controllers.response.UpdateFutResponseDTO;
import br.com.vemprofut.mappers.IFutMapper;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.HistoricoFutModel;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.services.IFutService;
import br.com.vemprofut.services.IHistoricoFutService;
import br.com.vemprofut.services.IPartidasService;
import br.com.vemprofut.services.query.IFutQueryService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FutService implements IFutService {

  @Autowired private IFutQueryService queryService;

  @Autowired private IFutMapper mapper;

  @Autowired private FutRepository repository;

  @Autowired private IPartidasService partidasService;

  @Autowired private IPeladeiroQueryService peladeiroQueryService;

  @Autowired private IHistoricoFutService historicoFutService;

  @Override
  @Transactional
  public SaveFutResponseDTO create(SaveFutRequestDTO dto) {
    queryService.verifyNomeFutExist(dto.nome());
    var peladeiro = peladeiroQueryService.verifyPeladeiroExist(dto.administradorPeladeiroId());

    FutModel saved = repository.save(mapper.saveRequestToModel(dto));

    HistoricoFutModel historico = historicoFutService.create();
    saved.setHistoricoFutId(historico);
    saved.setAdministradorPeladeiro(peladeiro);
    return mapper.toSaveResponse(repository.save(saved));
  }

  @Override
  @Transactional(readOnly = true)
  public FutDetailsResponse findById(Long id) {
    var futModel = queryService.verifyFutExistRetorn(id);
    return mapper.modelToDetailsResponse(futModel);
  }

  @Override
  @Transactional(readOnly = true)
  public FutModel findByIdModel(Long id) {
    return queryService.verifyFutExistRetorn(id);
  }

  @Override
  @Transactional(readOnly = true)
  public FutDTO findByNome(String nome) {
    var futModel = queryService.verifyNomeFutExistRetorn(nome);
    return mapper.toDTO(futModel);
  }

  @Override
  @Transactional(readOnly = true)
  public List<FutDTO> findAll() {
    return repository.findAll().stream().map(mapper::toDTO).toList();
  }

  @Override
  @Transactional
  public UpdateFutResponseDTO update(Long id, UpdateFutRequestDTO dto) {
    var retorno = queryService.verifyFutExistRetorn(id);

    retorno.setJogadoresPorTime(dto.jogadoresPorTime());
    retorno.setTempoMaxPartida(dto.tempoMaxPartida());
    retorno.setMaxGolsVitoria(dto.maxGolsVitoria());

    return mapper.modelToUpdateResponse(repository.save(retorno));
  }

  @Override
  @Transactional
  public void delete(Long id) {
    queryService.verifyFutExist(id);
    repository.deleteById(id);
  }

  @Override
  @Transactional
  public void criarPartida(Boolean jogadoresReservas, FutModel futModel) {

    partidasService.create(jogadoresReservas, futModel);
  }

  // TODO: Adicionar editores

  @Override
  @Transactional
  public void addPeladeiro(FutDTO futDTO, PeladeiroDTO peladeiroDTO) {
    // TODO:Verity
    futDTO.peladeiros().add(peladeiroDTO.id());
  }

  @Override
  @Transactional
  public void addCartoes(FutDTO futDTO, CartoesDTO cartoesDTO) {
    // TODO:Verity
    futDTO.cartoes().add(cartoesDTO.id());
  }
}
