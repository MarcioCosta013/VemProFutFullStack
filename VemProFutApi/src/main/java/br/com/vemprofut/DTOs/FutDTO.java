package br.com.vemprofut.DTOs;

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
