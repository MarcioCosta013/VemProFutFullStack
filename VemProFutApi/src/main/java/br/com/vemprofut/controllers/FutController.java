package br.com.vemprofut.controllers;

import br.com.vemprofut.controllers.request.*;
import br.com.vemprofut.controllers.response.*;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.services.IFutService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fut/")
@AllArgsConstructor
public class FutController {

  private final IFutService futService;

  @PostMapping
  @Operation(summary = "Cadastra um novo Fut")
  public ResponseEntity<SaveFutResponseDTO> create(
      @Valid @RequestBody final SaveFutRequestDTO requestDTO) {
    var response = futService.create(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("{id}")
  @Operation(summary = "Busca um Fut pelo ID")
  public ResponseEntity<FutDetailsResponse> findByFut(@PathVariable final Long id) {
    return ResponseEntity.ok(futService.findById(id));
  }

  @PutMapping("{id}")
  @Operation(summary = "Altera um Fut já cadastrado, informando o id")
  public ResponseEntity<UpdateFutResponseDTO> update(
      @PathVariable final Long id, UpdateFutRequestDTO dto) {
    var obj = futService.update(id, dto);
    return ResponseEntity.ok(obj);
  }

  @DeleteMapping("{id}")
  @Operation(summary = "Apaga um Fut por meio do id, cuidado! ")
  public ResponseEntity<Void> delete(@PathVariable final Long id) {
    futService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("partida")
  @Operation(summary = "Criar uma nova partida...")
  public ResponseEntity<SavePartidasResponseDTO> criarPartida(
      @RequestBody SavePartidaRequestDTO requestDTO) {
    FutModel futModel = futService.findByIdModel(requestDTO.futId());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(futService.criarPartida(requestDTO, futModel));
  }

  @PostMapping("partidaslist")
  @Operation(summary = "Cria varias partidas de uma só vez, todas com resultados preenchidos")
  public ResponseEntity<List<SavePartidasResponseDTO>> criarPartidasLista(
      @RequestBody List<SavePartidaRequestDTO> requestDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(futService.criarPartidasList(requestDTO));
  }

  @PostMapping("add-peladeiro")
  @Operation(summary = "Adiciona um peladeiro a lista de peladeiros cadastrado no fut")
  public ResponseEntity<Void> adicionarPeladeiroLista(
      @RequestBody AddPeladeiroInFutListRequestDTO addPeladeiroRequestDTO) {
    futService.addPeladeiro(addPeladeiroRequestDTO);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("lista-peladeiros/{idFut}")
  @Operation(summary = "busca a lista de todos peldadeiros cadastrados no fut")
  public ResponseEntity<List<PeladeiroResponseDTO>> listarPeladeirosCadastrados(
      @PathVariable final Long idFut) {
    return ResponseEntity.ok().body(futService.listarPeladeiroCadastradosFut(idFut));
  }

  @PostMapping("add-editor")
  @Operation(summary = "Adiciona um Editor a lista de editores de um fut em especifico")
  public ResponseEntity<Void> adicionarEditorLista(
      @RequestBody AddEditorInFutListResquestDTO editor) {
    futService.addEditor(editor);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("lista-editores/{idFut}")
  @Operation(summary = "busca a lista de editores de um fut em especifico")
  public ResponseEntity<List<PeladeiroNameIdResponseDTO>> listarEditoresFut(
      @PathVariable final Long idFut) {

    return ResponseEntity.ok().body(futService.listarEditoresCadastradosFut(idFut)); // testar
  }
}
