package br.com.vemprofut.controllers;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.request.UpdatePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.services.IPeladeiroService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("peladeiro/")
@AllArgsConstructor
public class PeladeiroController {

  private final IPeladeiroService peladeiroService;

  @PostMapping
  @Operation(
      summary = "Cadastra um novo peladeiro",
      tags = {"PeladeiroController"})
  public ResponseEntity<SavePeladeiroResponseDTO> create(
      @Valid @RequestBody final SavePeladeiroRequestDTO requestDTO) {
    var obj = peladeiroService.create(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(obj);
  }

  @GetMapping("{id}")
  @Operation(
      summary = "Busca um Peladeiro pelo id",
      tags = {"PeladeiroController"})
  public ResponseEntity<PeladeiroDetailResponse> findById(@PathVariable final Long id) {
    var obj = peladeiroService.findById(id);
    return ResponseEntity.ok(obj);
  }

  @PutMapping("{id}")
  @Operation(
      summary = "Faz alteraçoes no Peladeiro cujo id é informado.",
      tags = {"PeladeiroController"})
  public ResponseEntity<Void> update(
      @PathVariable final Long id, @Valid @RequestBody UpdatePeladeiroRequestDTO dto) {
    peladeiroService.update(id, dto);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  @Operation(
      summary = "Deleta o peladeiro cujo id foi informado. Cuidado!",
      tags = {"PeladeiroController"})
  public ResponseEntity<Void> delete(@PathVariable final Long id) {
    peladeiroService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(value = "uploadFoto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(
      summary = "Caso nao logado pelo gmail, enviar a foto do perfil",
      tags = {"PeladeiroController"})
  public ResponseEntity<String> uploadFotoPeladeiro(
      @PathVariable Long id, @RequestPart("file") MultipartFile file) throws IOException {
    peladeiroService.atualizarFoto(id, file);
    return ResponseEntity.ok("Foto salva!");
  }
}
