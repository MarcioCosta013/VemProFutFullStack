package br.com.vemprofut.controllers;

import br.com.vemprofut.controllers.request.SaveFutRequestDTO;
import br.com.vemprofut.controllers.request.UpdateFutRequestDTO;
import br.com.vemprofut.controllers.response.FutDetailsResponse;
import br.com.vemprofut.controllers.response.SaveFutResponseDTO;
import br.com.vemprofut.controllers.response.UpdateFutResponseDTO;
import br.com.vemprofut.services.IFutService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
}
