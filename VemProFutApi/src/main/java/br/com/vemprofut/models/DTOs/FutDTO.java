package br.com.vemprofut.models.DTOs;

import java.util.List;

public record FutDTO(
        Long id,
        String nome,
        Integer jogadoresPorTime,
        Integer tempoMaxPartida,
        Integer maxGolsPartida,
        Long historicoFutId,
        Long admPeladeiroId,
        List<Long> peladeiros
) { }
