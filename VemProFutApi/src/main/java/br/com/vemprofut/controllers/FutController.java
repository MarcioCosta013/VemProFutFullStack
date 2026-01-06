package br.com.vemprofut.controllers;

import br.com.vemprofut.controllers.request.*;
import br.com.vemprofut.controllers.response.*;
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
  public CompletableFuture<ResponseEntity<FutDetailsResponse>> findByFut(
      @PathVariable final Long id) {
    return futService.findById(id).thenApply(v -> ResponseEntity.ok().build());
  }

  @PutMapping("{id}")
  @Operation(
      summary = "Altera um Fut já cadastrado, informando o id",
      tags = {"FutController - CRUD Básico"})
  public CompletableFuture<ResponseEntity<UpdateFutResponseDTO>> update(
      @PathVariable final Long id, UpdateFutRequestDTO dto) {
    return futService.update(id, dto).thenApply(obj -> ResponseEntity.ok(obj));
  }

  @DeleteMapping("{id}")
  @Operation(
      summary = "Apaga um Fut por meio do id, cuidado! ",
      tags = {"FutController - CRUD Básico"})
  public CompletableFuture<ResponseEntity<Void>> delete(@PathVariable final Long id) {
    return futService.delete(id).thenApply(v -> ResponseEntity.noContent().build());
  }

  // =================== acoes partidas =======================

  @PostMapping("partida")
  @Operation(
      summary = "Criar uma nova partida...",
      tags = {"FutController - Ações Partidas"})
  public CompletableFuture<ResponseEntity<SavePartidasResponseDTO>> criarPartida(
      @RequestBody SavePartidaRequestDTO requestDTO) {
    return futService
        .findByIdModel(requestDTO.futId())
        .thenCompose(
            futModel ->
                futService
                    .criarPartida(requestDTO, futModel)
                    .thenApply(obj -> ResponseEntity.status(HttpStatus.CREATED).body(obj)));
  }

  @PostMapping("partidaslist")
  @Operation(
      summary = "Cria varias partidas de uma só vez, todas com resultados preenchidos",
      tags = {"FutController - Ações Partidas"})
  public CompletableFuture<ResponseEntity<List<SavePartidasResponseDTO>>> criarPartidasLista(
      @RequestBody List<SavePartidaRequestDTO> requestDTO) {
    return futService
        .criarPartidasList(requestDTO)
        .thenApply(obj -> ResponseEntity.status(HttpStatus.CREATED).body(obj));
  }

  // ================ lista peladeiro =====================

  @PostMapping("add-peladeiro")
  @Operation(
      summary = "Adiciona um peladeiro a lista de peladeiros cadastrado no fut",
      tags = {"FutController - Lista de Peladeiros"})
  public CompletableFuture<ResponseEntity<Void>> adicionarPeladeiroLista(
      @RequestBody AddPeladeiroInFutListRequestDTO addPeladeiroRequestDTO) {
    return futService
        .addPeladeiro(addPeladeiroRequestDTO)
        .thenApply(v -> ResponseEntity.noContent().build());
  }

  @GetMapping("lista-peladeiros/{idFut}")
  @Operation(
      summary = "busca a lista de todos peldadeiros cadastrados no fut",
      tags = {"FutController - Lista de Peladeiros"})
  public CompletableFuture<ResponseEntity<List<PeladeiroResponseDTO>>> listarPeladeirosCadastrados(
      @PathVariable final Long idFut) {
    return futService
        .listarPeladeiroCadastradosFut(idFut)
        .thenApply(obj -> ResponseEntity.ok().body(obj));
  }

  // =============== lista Editores ===========================

  @PostMapping("add-editor")
  @Operation(
      summary = "Adiciona um Editor a lista de editores de um fut em especifico",
      tags = {"FutController - Lista de Editores do Fut"})
  public CompletableFuture<ResponseEntity<Void>> adicionarEditorLista(
      @RequestBody AddEditorInFutListResquestDTO editor) {
    return futService.addEditor(editor).thenApply(v -> ResponseEntity.noContent().build());
  }

  @GetMapping("lista-editores/{idFut}")
  @Operation(
      summary = "busca a lista de editores de um fut em especifico",
      tags = {"FutController - Lista de Editores do Fut"})
  public CompletableFuture<ResponseEntity<List<PeladeiroNameIdResponseDTO>>> listarEditoresFut(
      @PathVariable final Long idFut) {
    return futService
        .listarEditoresCadastradosFut(idFut)
        .thenApply(obj -> ResponseEntity.ok().body(obj));
  }

  // ================= upload arquivos fotos ==================

  @PostMapping(value = "uploadFoto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(
      summary = "Para enviar a foto de capa do Fut",
      tags = {"FutController - Upload de Imagens"})
  public CompletableFuture<ResponseEntity<String>> uploadFotoFut(
      @PathVariable Long id, @RequestPart("file") MultipartFile file) {
    return futService
        .atualizarFotoCapa(id, file)
        .thenApply(obj -> ResponseEntity.ok("Foto de capa Salva!"));
  }

  // ======================== Banimentos =======================

  @PostMapping(value = "addBanimento")
  @Operation(
      summary = "Adicionando um peladeiro da lista para o banimento",
      tags = {"FutController - Banimento no Fut"})
  public CompletableFuture<ResponseEntity<Void>> adicionarBanimento(
      @RequestBody SaveBanimentoRequestDTO dto) {
    return futService.addBanimentoList(dto).thenApply(v -> ResponseEntity.noContent().build());
  }

  @GetMapping("lista-banidos/{idFut}")
  @Operation(
      summary = "Busca a lista de Banidos do Fut em questao",
      tags = {"FutController - Banimento no Fut"})
  public CompletableFuture<ResponseEntity<List<BanimentoDetailsResponseDTO>>> buscarListaBanidos(
      @PathVariable Long idFut) {
    return futService.findAllBanidos(idFut).thenApply(obj -> ResponseEntity.ok().body(obj));
  }

  @DeleteMapping("delete-banimento/{idFut}/{idPeladeiro}")
  @Operation(
      summary = "retira um peladeiro da lista de banidos",
      tags = {"FutController - Banimento no Fut"})
  public CompletableFuture<ResponseEntity<Void>> retirandoBanimento(
      @PathVariable Long idFut, @PathVariable Long idPeladeiro) {
    return futService
        .removeBanido(idPeladeiro, idFut)
        .thenApply(v -> ResponseEntity.noContent().build());
  }
}
