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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SavePeladeiroResponseDTO> create (@Valid @RequestBody final SavePeladeiroRequestDTO requestDTO){
        var obj = peladeiroService.create(requestDTO);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id ,@Valid @RequestBody UpdatePeladeiroRequestDTO dto){
        peladeiroService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<PeladeiroDetailResponse> findById (@PathVariable final Long id){
        var obj = peladeiroService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id){
        peladeiroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
