package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.controllers.request.*;
import br.com.vemprofut.controllers.response.*;
import br.com.vemprofut.mappers.IEditorMapper;
import br.com.vemprofut.mappers.IFutMapper;
import br.com.vemprofut.mappers.IPartidasMapper;
import br.com.vemprofut.mappers.IPeladeiroMapper;
import br.com.vemprofut.models.*;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.repositories.*;
import br.com.vemprofut.services.*;
import br.com.vemprofut.services.query.IFutQueryService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

  @Autowired private IPeladeiroMapper peladeiroMapper;

  @Autowired private IHistoricoFutService historicoFutService;

  @Autowired private CartoesRepository cartoesRepository;

  @Autowired private GolsPartidaRepository golsRepository;

  @Autowired private IEditorService editorService;

  @Autowired private IEditorMapper editorMapper;

  @Autowired private IBanimentoService banidoService;

  // ======================== CRUD basico ==========================

  @Override
  @Transactional
  public SaveFutResponseDTO create(SaveFutRequestDTO dto) {
    queryService.verifyNomeFutExist(dto.nome());
    var peladeiro = peladeiroQueryService.verifyPeladeiroExist(dto.administradorPeladeiro());

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

  // ======================== acoes partidas ==========================

  @Override
  @Transactional
  public SavePartidasResponseDTO criarPartida(SavePartidaRequestDTO requestDTO, FutModel futModel) {
    return partidasService.create(requestDTO, futModel);
  }

  @Override
  @Transactional
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

          // Para sincronizar a tabela intermédiaria da relacao @ManyToMany.
          partida.getPeladeiros().add(peladeiroModel);
          peladeiroModel
              .getPartidas()
              .add(partida); // Esse adiciona pa tabela: "esta_peladeiro_partidas".

          // para garantir que vai ser salvo.
          peladeiroRepository.save(
              peladeiroModel); // Esse salva em PeladeiroModel (que é onde a table intermediaria foi
          // criada)

          log.info("Peladeiro adicionado");
        }
      }
      partidaList.add(partida);
    }
    return partidasMapper.toResponseList(partidaList);
  }

  // ======================== lista peladeiro ========================

  @Override
  @Transactional
  public void addPeladeiro(AddPeladeiroInFutListRequestDTO requestDTO) {
    PeladeiroModel peladeiroModel =
        peladeiroQueryService.verifyPeladeiroExist(requestDTO.peladeiroId());
    FutModel futModel = queryService.verifyFutExistRetorn(requestDTO.futId());
    queryService.verityPeladeiroInList(futModel, peladeiroModel);
    log.info("Verificacao do Peladeiro e do Fut realizadas com sucesso! Salvando dados...");

    futModel
        .getPeladeiros()
        .add(peladeiroModel); // Esse adiciona a tabela: "participa_peladeiro_fut".
    repository.save(
        futModel); // Esse salva em FutModel (que é onde a table intermediaria foi criada)
  }

  @Override
  @Transactional
  public List<PeladeiroResponseDTO> listarPeladeiroCadastradosFut(Long futId) {
    FutModel futModel = queryService.verifyFutExistRetornListPeladeiro(futId); /*
                                                                                Esse metodo retorna futModel já com peladeiros carregado... resolvendo o problema abaixo.
                                                                                @ManyToMany é LAZY, Ou seja:
                                                                                - O Hibernate carrega os objetos da lista somente quando necessário.
                                                       /                         - MAS… apenas carrega os campos que estão presentes na tabela Many-to-Many(id , nome, email...).
                                                                                - A tabela intermediária NÃO contém o ID do Peladeiro (Ex. id: null nome: null ...).
                                                                                - Portanto o Hibernate cria um “proxy”(Pense em um proxy como um “representante” de um objeto real.)
                                                                                 com ID NULL até realmente precisar buscar do banco.
                                                                                */
    log.info("Verificacao de existencia de Fut realizada com sucesso!");

    List<PeladeiroResponseDTO> listResponce = new ArrayList<>();

    for (PeladeiroModel p : futModel.getPeladeiros()) {
      listResponce.add(peladeiroMapper.modelToPeladeiroResponse(p));
    }
    return listResponce;
  }

  // ===================== lista Editores =============================
  @Override
  @Transactional
  public void addEditor(AddEditorInFutListResquestDTO resquestDTO) {
    EditorModel editorModel = new EditorModel();

    // Verificacoes
    FutModel futModel = queryService.verifyFutExistRetorn(resquestDTO.fut());
    PeladeiroModel peladeiroModel =
        peladeiroQueryService.verifyPeladeiroExist(resquestDTO.peladeiro());
    queryService.verifyPeladeiroExistInListOrAdm(futModel, peladeiroModel);

    editorModel.setFut(futModel);
    editorModel.setPeladeiro(peladeiroModel);
    // Salvando Editor
    editorService.create(editorModel);
    // Adicionando Editor na lista de Editores de Fut.
    futModel.getEditores().add(editorModel);
    repository.save(futModel);
    // Adicionado Editor na lista de Editores de Peladeiro.
    peladeiroModel.getEditores().add(editorModel);
    peladeiroRepository.save(peladeiroModel);
  }

  @Override
  @Transactional
  public List<PeladeiroNameIdResponseDTO> listarEditoresCadastradosFut(Long idFut) {
    FutModel futModel = queryService.verifyFutExistRetornListEditores(idFut);
    log.info("Verificacao de existencia de Fut realizada com sucesso!");

    List<PeladeiroNameIdResponseDTO> listResponse = new ArrayList<>();

    for (EditorModel e : futModel.getEditores()) {
      listResponse.add(editorMapper.toResponseNameId(e));
    }

    return listResponse;
  }

  // ===================== upload arquivos fotos ======================

  @Override
  @Transactional
  public void atualizarFotoCapa(Long id, MultipartFile file) {
    queryService.verifyFutSaveFile(id, file);
  }

  // ============================= Banimentos ============================

  @Override
  @Transactional
  public SaveBanimentoResponseDTO addBanimentoList(SaveBanimentoRequestDTO dto) {
    FutModel futModel = queryService.verifyFutExistRetorn(dto.fut());
    PeladeiroModel peladeiroModel = peladeiroQueryService.verifyPeladeiroExist(dto.peladeiro());

    // Verificacao de existe o peladeiro em questao na lista de peladeiro...
    queryService.verifyBanidoListPeladeiros(futModel, peladeiroModel);

    return banidoService.create(dto);
  }

  @Override
  @Transactional
  public List<BanimentoDetailsResponseDTO> findAllBanidos(Long idFut) {
    return banidoService.findAll(idFut);
  }

  @Override
  @Transactional
  public void removeBanido(Long idPeladeiro, Long idFut) {
    // TODO: retirar um Banido da lista
    queryService.verifyBanidoListPeladeiros(
        queryService.verifyFutExistRetorn(idFut),
        peladeiroQueryService.verifyPeladeiroExist(idPeladeiro));

    banidoService.delete(idPeladeiro);
  }
}
