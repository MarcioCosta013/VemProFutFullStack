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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Peladeiro criado com sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Dados inválidos ou e-mail já cadastrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                {
                                  "error": "E-mail já cadastrado"
                                }
                            """))
            )
    })
    public ResponseEntity<SavePeladeiroResponseDTO> create (@Valid @RequestBody final SavePeladeiroRequestDTO requestDTO){
        var obj = peladeiroService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @GetMapping("{id}")
    public ResponseEntity<PeladeiroDetailResponse> findById (@PathVariable final Long id){
        var obj = peladeiroService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id ,@Valid @RequestBody UpdatePeladeiroRequestDTO dto){
        peladeiroService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id){
        peladeiroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
