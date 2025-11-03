package br.com.vemprofut.models.DTOs;

import java.util.List;

public record PartidasDTO(
        Long id,
        Boolean reservas,
        Long idFut,
        List<Long> golsPartida,
        List<Long> peladeiros
) { }
