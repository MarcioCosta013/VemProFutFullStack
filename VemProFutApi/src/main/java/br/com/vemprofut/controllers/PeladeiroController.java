package br.com.vemprofut.controllers;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.request.UpdatePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.UpdatePeladeiroResponseDTO;
import br.com.vemprofut.mappers.IPeladeiroMapper;
import br.com.vemprofut.services.IPeladeiroService;
import br.com.vemprofut.services.implementacao.PeladeiroService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("peladeiro/")
@AllArgsConstructor
public class PeladeiroController {

    private final IPeladeiroService peladeiroService;

    @PostMapping
    @Operation(summary = "Cadastra um novo peladeiro")
    public ResponseEntity<SavePeladeiroResponseDTO> create (@Valid @RequestBody final SavePeladeiroRequestDTO requestDTO){
        var obj = peladeiroService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @GetMapping("{id}")
    @Operation(summary = "Busca um Peladeiro pelo id")
    public ResponseEntity<PeladeiroDetailResponse> findById (@PathVariable final Long id){
        var obj = peladeiroService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("{id}")
    @Operation(summary = "Faz alteraçoes no Peladeiro cujo id é informado.")
    public ResponseEntity<Void> update(@PathVariable final Long id ,@Valid @RequestBody UpdatePeladeiroRequestDTO dto){
        peladeiroService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deleta o peladeiro cujo id foi informado. Cuidado!")
    public ResponseEntity<Void> delete(@PathVariable final Long id){
        peladeiroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
