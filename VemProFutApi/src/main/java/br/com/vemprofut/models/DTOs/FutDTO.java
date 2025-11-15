package br.com.vemprofut.models.DTOs;

import java.util.List;

public record FutDTO(
    Long id,
    String nome,
    Integer jogadoresPorTime,
    Integer tempoMaxPartida,
    Integer maxGolsVitoria,
    Long historicoFutId,
    Long administradorPeladeiroId,
    List<Long> editores,
    List<Long> peladeiros,
    List<Long> cartoes) {}
