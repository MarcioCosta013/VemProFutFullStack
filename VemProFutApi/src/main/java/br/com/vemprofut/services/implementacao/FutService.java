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
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
  @Async("defaultExecutor")
  public CompletableFuture<SaveFutResponseDTO> create(SaveFutRequestDTO dto) {
    queryService.verifyNomeFutExist(dto.nome());
    return peladeiroQueryService
        .verifyPeladeiroExist(dto.administradorPeladeiro())
        .thenApply(
            peladeiroModel -> {
              FutModel saved = repository.save(mapper.saveRequestToModel(dto));

              HistoricoFutModel historico = historicoFutService.create();
              saved.setHistoricoFutId(historico);
              saved.setAdministradorPeladeiro(peladeiroModel);
              return mapper.toSaveResponse(repository.save(saved));
            });
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<FutDetailsResponse> findById(Long id) {
    return queryService
        .verifyFutExistRetorn(id)
        .thenApply(futModel -> mapper.modelToDetailsResponse(futModel));
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<FutModel> findByIdModel(Long id) {
    return queryService.verifyFutExistRetorn(id);
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<FutDTO> findByNome(String nome) {
    return queryService
        .verifyNomeFutExistRetorn(nome)
        .thenApply(futModel -> mapper.toDTO(futModel));
  }

  @Override
  @Transactional(readOnly = true)
  @Async("defaultExecutor")
  public CompletableFuture<List<FutDTO>> findAll() {
    return CompletableFuture.completedFuture(
        repository.findAll().stream().map(mapper::toDTO).toList());
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<UpdateFutResponseDTO> update(Long id, UpdateFutRequestDTO dto) {
    return queryService
        .verifyFutExistRetorn(id)
        .thenApply(
            futModel -> {
              futModel.setJogadoresPorTime(dto.jogadoresPorTime());
              futModel.setTempoMaxPartida(dto.tempoMaxPartida());
              futModel.setMaxGolsVitoria(dto.maxGolsVitoria());

              return mapper.modelToUpdateResponse(repository.save(futModel));
            });
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<Void> delete(Long id) {
    queryService.verifyFutExistRetorn(id);
    repository.deleteById(id);
    return CompletableFuture.completedFuture(null);
  }

  // ======================== acoes partidas ==========================

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<SavePartidasResponseDTO> criarPartida(
      SavePartidaRequestDTO requestDTO, FutModel futModel) {
    return partidasService.create(requestDTO, futModel);
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<List<SavePartidasResponseDTO>> criarPartidasList(
      List<SavePartidaRequestDTO> requestDTOS) {
    // dispara cada DTO em paralelo
    List<CompletableFuture<PartidasModel>> futeres =
        requestDTOS.stream()
            .map(this::criarPartidasAsync) // cada DTO vira um future de PartidasModel
            .toList();

    return CompletableFuture.allOf(futeres.toArray(new CompletableFuture[0]))
        .thenApply(
            v ->
                futeres.stream()
                    .map(CompletableFuture::join) // pega cada PartidasModel
                    .map(partidasMapper::toResponse) // transforma em DTO
                    .toList());
  }

  private CompletableFuture<PartidasModel> criarPartidasAsync(SavePartidaRequestDTO dto) {
    PartidasModel partida = new PartidasModel();

    return queryService
        .verifyFutExistRetorn(dto.futId()) // <-- precisa retornar aqui
        .thenCompose(
            futModel -> {
              partida.setReservas(dto.reservas());
              partida.setFutId(futModel);
              partidasRepository.save(partida);

              // Cartões async
              CompletableFuture<Void> cartoesFuture =
                  CompletableFuture.allOf(
                      dto.cartoes() == null
                          ? new CompletableFuture[0]
                          : dto.cartoes().stream()
                              .map(
                                  c ->
                                      peladeiroQueryService
                                          .verifyPeladeiroExist(c.peladeiroId())
                                          .thenAccept(
                                              peladeiroModel -> {
                                                CartoesModel cartoesModel = new CartoesModel();
                                                cartoesModel.setFut(futModel);
                                                cartoesModel.setTipoCartao(c.tipoCartao());
                                                cartoesModel.setPeladeiro(peladeiroModel);
                                                cartoesModel.setPartida(partida);
                                                cartoesRepository.save(cartoesModel);
                                                partida.getCartoes().add(cartoesModel);
                                              }))
                              .toArray(CompletableFuture[]::new));

              // Gols async
              CompletableFuture<Void> golsFuture =
                  CompletableFuture.allOf(
                      dto.gols() == null
                          ? new CompletableFuture[0]
                          : dto.gols().stream()
                              .map(
                                  g ->
                                      peladeiroQueryService
                                          .verifyPeladeiroExist(g.peladeiro())
                                          .thenAccept(
                                              peladeiroModel -> {
                                                GolsPartidaModel golsPartidaModel =
                                                    new GolsPartidaModel();
                                                golsPartidaModel.setPeladeiro(peladeiroModel);
                                                golsPartidaModel.setPartida(partida);
                                                golsRepository.save(golsPartidaModel);
                                                partida.getGolsPartida().add(golsPartidaModel);
                                              }))
                              .toArray(CompletableFuture[]::new));

              // Peladeiros async
              CompletableFuture<Void> peladeirosFuture =
                  CompletableFuture.allOf(
                      dto.peladeiros() == null
                          ? new CompletableFuture[0]
                          : dto.peladeiros().stream()
                              .map(
                                  p ->
                                      peladeiroQueryService
                                          .verifyPeladeiroExist(p.id())
                                          .thenAccept(
                                              peladeiroModel -> {
                                                peladeiroModel.addPartida(partida);
                                                peladeiroRepository.save(peladeiroModel);
                                              }))
                              .toArray(CompletableFuture[]::new));

              // Espera todos os subtarefas terminarem
              return CompletableFuture.allOf(cartoesFuture, golsFuture, peladeirosFuture)
                  .thenApply(v -> partida);
            });
  }

  // ======================== lista peladeiro ========================

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<Void> addPeladeiro(AddPeladeiroInFutListRequestDTO requestDTO) {
    return peladeiroQueryService
        .verifyPeladeiroExist(requestDTO.peladeiroId())
        .thenCompose(
            peladeiroModel ->
                queryService
                    .verifyFutExistRetorn(requestDTO.futId())
                    .thenCompose(
                        futModel ->
                            queryService
                                .verityPeladeiroInList(futModel, peladeiroModel)
                                .thenRun(
                                    () -> {
                                      log.info(
                                          "Verificacao do Peladeiro e do Fut realizadas com sucesso! Salvando dados...");

                                      futModel
                                          .getPeladeiros()
                                          .add(peladeiroModel); // Esse adiciona a tabela:
                                      // "participa_peladeiro_fut".
                                      repository.save(
                                          futModel); // Esse salva em FutModel (que é onde a table
                                      // intermediaria foi criada)
                                    })));
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<List<PeladeiroResponseDTO>> listarPeladeiroCadastradosFut(Long futId) {
    return queryService
        .verifyFutExistRetornListPeladeiro(futId) /*
                                                                                Esse metodo retorna futModel já com peladeiros carregado... resolvendo o problema abaixo.
                                                                                @ManyToMany é LAZY, Ou seja:
                                                                                - O Hibernate carrega os objetos da lista somente quando necessário.
                                                                                - MAS… apenas carrega os campos que estão presentes na tabela Many-to-Many(id , nome, email...).
                                                                                - A tabela intermediária NÃO contém o ID do Peladeiro (Ex. id: null nome: null ...).
                                                                                - Portanto o Hibernate cria um “proxy”(Pense em um proxy como um “representante” de um objeto real.)
                                                                                 com ID NULL até realmente precisar buscar do banco.
                                                                                */
        .thenApply(
            futModel -> {
              log.info("Verificacao de existencia de Fut realizada com sucesso!");

              List<PeladeiroResponseDTO> listResponce = new ArrayList<>();

              for (PeladeiroModel p : futModel.getPeladeiros()) {
                listResponce.add(peladeiroMapper.modelToPeladeiroResponse(p));
              }
              return listResponce;
            });
  }

  // ===================== lista Editores =============================
  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<Void> addEditor(AddEditorInFutListResquestDTO resquestDTO) {
    EditorModel editorModel = new EditorModel();
    // Verificacoes
    return queryService
        .verifyFutExistRetorn(resquestDTO.fut())
        .thenCompose(
            futModel ->
                peladeiroQueryService
                    .verifyPeladeiroExist(resquestDTO.peladeiro())
                    .thenAccept(
                        peladeiroModel -> {
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
                        }));
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<List<PeladeiroNameIdResponseDTO>> listarEditoresCadastradosFut(
      Long idFut) {
    return queryService
        .verifyFutExistRetornListEditores(idFut)
        .thenApply(
            futModel -> {
              log.info("Verificacao de existencia de Fut realizada com sucesso!");

              List<PeladeiroNameIdResponseDTO> listResponse = new ArrayList<>();

              for (EditorModel e : futModel.getEditores()) {
                listResponse.add(editorMapper.toResponseNameId(e));
              }

              return listResponse;
            });
  }

  // ===================== upload arquivos fotos ======================

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<Void> atualizarFotoCapa(Long id, MultipartFile file) {
    queryService.verifyFutSaveFile(id, file);
    return CompletableFuture.completedFuture(null);
  }

  // ============================= Banimentos ============================

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<SaveBanimentoResponseDTO> addBanimentoList(SaveBanimentoRequestDTO dto) {
    return queryService
        .verifyFutExistRetorn(dto.fut())
        .thenCompose(
            futModel ->
                peladeiroQueryService
                    .verifyPeladeiroExist(dto.peladeiro())
                    .thenCompose(
                        peladeiroModel -> {
                          // Verificacao de existe o peladeiro em questao na lista de peladeiro...
                          queryService.verifyBanidoListPeladeiros(futModel, peladeiroModel);

                          return banidoService.create(dto);
                        }));
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<List<BanimentoDetailsResponseDTO>> findAllBanidos(Long idFut) {
    return banidoService.findAll(idFut);
  }

  @Override
  @Transactional
  @Async("defaultExecutor")
  public CompletableFuture<Void> removeBanido(Long idPeladeiro, Long idFut) {
    return queryService
        .verifyFutExistRetorn(idFut)
        .thenCompose(
            futModel ->
                peladeiroQueryService
                    .verifyPeladeiroExist(idPeladeiro)
                    .thenCompose(
                        peladeiroModel -> {
                          queryService.verifyBanidoListPeladeiros(futModel, peladeiroModel);
                          return banidoService.delete(idPeladeiro);
                        }));
  }
}
