package br.com.vemprofut.DTOs;

public record HistoricoFutDTO(
        Long id,
        Integer golsTotal,
        Integer totalPartidas,
        String timeMaisVitorias
) { }
