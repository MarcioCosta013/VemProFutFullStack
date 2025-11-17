package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.controllers.request.*;
import br.com.vemprofut.controllers.response.*;
import br.com.vemprofut.mappers.IFutMapper;
import br.com.vemprofut.mappers.IPartidasMapper;
import br.com.vemprofut.models.*;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.repositories.*;
import br.com.vemprofut.services.IFutService;
import br.com.vemprofut.services.IHistoricoFutService;
import br.com.vemprofut.services.IPartidasService;
import br.com.vemprofut.services.query.IFutQueryService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FutService implements IFutService {

  @Autowired private IFutQueryService queryService;

  @Autowired private IFutMapper mapper;

  @Autowired private IPartidasMapper partidasMapper;

  @Autowired private FutRepository repository;

  @Autowired private IPartidasService partidasService;

  @Autowired private PartidasRepository partidasRepository;

  @Autowired private IPeladeiroQueryService peladeiroQueryService;

  @Autowired private PeladeiroRepository peladeiroRepository;

  @Autowired private IHistoricoFutService historicoFutService;

  @Autowired private CartoesRepository cartoesRepository;

  @Autowired private GolsPartidaRepository golsRepository;

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
    queryService.verifyFutExistRetorn(id);
    repository.deleteById(id);
  }

  @Override
  @Transactional
  public SavePartidasResponseDTO criarPartida(SavePartidaRequestDTO requestDTO, FutModel futModel) {
    // TODO: criar e implementar os DTOs de request e responde de Partida
    // TODO: implementar gols
    // TODO: implementar cartoes
    // TODO: implementar lista de peladeiros que irão jogar
    return partidasService.create(requestDTO, futModel);
  }

  public List<SavePartidasResponseDTO> criarPartidasList(List<SavePartidaRequestDTO> requestDTOS) {

    List<PartidasModel> partidaList = new ArrayList<>();

    for (SavePartidaRequestDTO dto : requestDTOS) {
      // 1 - criando partida
      log.info("criando Partida...");
      PartidasModel partida = new PartidasModel();
      FutModel futModel = queryService.verifyFutExistRetorn(dto.futId());
      partida.setReservas(dto.reservas());
      partida.setFutId(futModel);

      // salvando partida para pegar o id.
      partidasRepository.save(partida);
      log.info("Partida criada, adicionando Cartao...");

      // 2 - criando cartoes
      if (dto.cartoes() != null) {
        for (CartoesRequestDTO c : dto.cartoes()) {
          CartoesModel cartoesModel = new CartoesModel();
          cartoesModel.setFut(queryService.verifyFutExistRetorn(dto.futId()));
          cartoesModel.setTipoCartao(c.tipoCartao());
          cartoesModel.setPeladeiro(peladeiroQueryService.verifyPeladeiroExist(c.peladeiroId()));
          cartoesModel.setPartida(partida);

          cartoesRepository.save(cartoesModel);
          partida.getCartoes().add(cartoesModel); // pegar a lista para adicionar nela.
          log.info("Cartao adicionado, Adicionando Gol ");
        }
      }

      // 3 - criando Gols
      if (dto.gols() != null) {
        for (GolsPartidaRequestDTO g : dto.gols()) {
          GolsPartidaModel golsPartida = new GolsPartidaModel();
          golsPartida.setPeladeiro(peladeiroQueryService.verifyPeladeiroExist(g.peladeiro()));
          golsPartida.setPartida(partida);

          golsRepository.save(golsPartida);
          partida.getGolsPartida().add(golsPartida);
          log.info("Gol adicionado, Adicionando Peladeiro");
        }
      }

      // 4 - criando peladeiro
      if (dto.peladeiros() != null) {
        for (PeladeiroRequestDTO p : dto.peladeiros()) {
          PeladeiroModel peladeiroModel = peladeiroQueryService.verifyPeladeiroExist(p.id());

          //Para sincronizar a tabela intermédiaria da relacao @ManyToMany.
          partida.getPeladeiros().add(peladeiroModel);
          peladeiroModel.getPartidas().add(partida); //Esse adiciona pa tabela: "esta_peladeiro_partidas".
          futModel.getPeladeiros().add(peladeiroModel); //Esse adiciona a tabela: "participa_peladeiro_fut".

          //para garantir que vai ser salvo.
          repository.save(futModel);
          peladeiroRepository.save(peladeiroModel);

          log.info("Peladeiro adicionado");
        }
      }
      partidaList.add(partida);
    }
    return partidasMapper.toResponseList(partidaList);
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
