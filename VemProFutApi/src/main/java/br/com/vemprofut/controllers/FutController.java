package br.com.vemprofut.controllers;

import br.com.vemprofut.controllers.request.*;
import br.com.vemprofut.controllers.response.*;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.services.IFutService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("fut/")
@AllArgsConstructor
public class FutController {

  private final IFutService futService;

  // =============== CRUD basico ==========================
  @PostMapping
  @Operation(
      summary = "Cadastra um novo Fut",
      tags = {"FutController - CRUD Básico"})
  public CompletableFuture<ResponseEntity<SaveFutResponseDTO>> create(
      @Valid @RequestBody final SaveFutRequestDTO requestDTO) {
    return futService
        .create(requestDTO)
        .thenApply(obj -> ResponseEntity.status(HttpStatus.CREATED).body(obj));
  }

  @GetMapping("{id}")
  @Operation(
      summary = "Busca um Fut pelo ID",
      tags = {"FutController - CRUD Básico"})
  public ResponseEntity<FutDetailsResponse> findByFut(@PathVariable final Long id) {
    return ResponseEntity.ok(futService.findById(id));
  }

  @PutMapping("{id}")
  @Operation(
      summary = "Altera um Fut já cadastrado, informando o id",
      tags = {"FutController - CRUD Básico"})
  public ResponseEntity<UpdateFutResponseDTO> update(
      @PathVariable final Long id, UpdateFutRequestDTO dto) {
    var obj = futService.update(id, dto);
    return ResponseEntity.ok(obj);
  }

  @DeleteMapping("{id}")
  @Operation(
      summary = "Apaga um Fut por meio do id, cuidado! ",
      tags = {"FutController - CRUD Básico"})
  public ResponseEntity<Void> delete(@PathVariable final Long id) {
    futService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // =================== acoes partidas =======================

  @PostMapping("partida")
  @Operation(
      summary = "Criar uma nova partida...",
      tags = {"FutController - Ações Partidas"})
  public ResponseEntity<SavePartidasResponseDTO> criarPartida(
      @RequestBody SavePartidaRequestDTO requestDTO) {
    FutModel futModel = futService.findByIdModel(requestDTO.futId());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(futService.criarPartida(requestDTO, futModel));
  }

  @PostMapping("partidaslist")
  @Operation(
      summary = "Cria varias partidas de uma só vez, todas com resultados preenchidos",
      tags = {"FutController - Ações Partidas"})
  public ResponseEntity<List<SavePartidasResponseDTO>> criarPartidasLista(
      @RequestBody List<SavePartidaRequestDTO> requestDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(futService.criarPartidasList(requestDTO));
  }

  // ================ lista peladeiro =====================

  @PostMapping("add-peladeiro")
  @Operation(
      summary = "Adiciona um peladeiro a lista de peladeiros cadastrado no fut",
      tags = {"FutController - Lista de Peladeiros"})
  public ResponseEntity<Void> adicionarPeladeiroLista(
      @RequestBody AddPeladeiroInFutListRequestDTO addPeladeiroRequestDTO) {
    futService.addPeladeiro(addPeladeiroRequestDTO);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("lista-peladeiros/{idFut}")
  @Operation(
      summary = "busca a lista de todos peldadeiros cadastrados no fut",
      tags = {"FutController - Lista de Peladeiros"})
  public ResponseEntity<List<PeladeiroResponseDTO>> listarPeladeirosCadastrados(
      @PathVariable final Long idFut) {
    return ResponseEntity.ok().body(futService.listarPeladeiroCadastradosFut(idFut));
  }

  // =============== lista Editores ===========================

  @PostMapping("add-editor")
  @Operation(
      summary = "Adiciona um Editor a lista de editores de um fut em especifico",
      tags = {"FutController - Lista de Editores do Fut"})
  public ResponseEntity<Void> adicionarEditorLista(
      @RequestBody AddEditorInFutListResquestDTO editor) {
    futService.addEditor(editor);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("lista-editores/{idFut}")
  @Operation(
      summary = "busca a lista de editores de um fut em especifico",
      tags = {"FutController - Lista de Editores do Fut"})
  public ResponseEntity<List<PeladeiroNameIdResponseDTO>> listarEditoresFut(
      @PathVariable final Long idFut) {

    return ResponseEntity.ok().body(futService.listarEditoresCadastradosFut(idFut)); // TODO:testar
  }

  // ================= upload arquivos fotos ==================

  @PostMapping(value = "uploadFoto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(
      summary = "Para enviar a foto de capa do Fut",
      tags = {"FutController - Upload de Imagens"})
  public ResponseEntity<String> uploadFotoFut(
      @PathVariable Long id, @RequestPart("file") MultipartFile file) {
    futService.atualizarFotoCapa(id, file);
    return ResponseEntity.ok("Foto de capa Salva!");
  }

  // ======================== Banimentos =======================

  @PostMapping(value = "addBanimento")
  @Operation(
      summary = "Adicionando um peladeiro da lista para o banimento",
      tags = {"FutController - Banimento no Fut"})
  public ResponseEntity<Void> adicionarBanimento(@RequestBody SaveBanimentoRequestDTO dto) {
    futService.addBanimentoList(dto);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("lista-banidos/{idFut}")
  @Operation(
      summary = "Busca a lista de Banidos do Fut em questao",
      tags = {"FutController - Banimento no Fut"})
  public ResponseEntity<List<BanimentoDetailsResponseDTO>> buscarListaBanidos(
      @PathVariable Long idFut) {
    return ResponseEntity.ok().body(futService.findAllBanidos(idFut));
  }

  @DeleteMapping("delete-banimento/{idFut}/{idPeladeiro}")
  @Operation(
      summary = "retira um peladeiro da lista de banidos",
      tags = {"FutController - Banimento no Fut"})
  public ResponseEntity<Void> retirandoBanimento(
      @PathVariable Long idFut, @PathVariable Long idPeladeiro) {
    futService.removeBanido(idPeladeiro, idFut);
    return ResponseEntity.noContent().build();
  }
}
