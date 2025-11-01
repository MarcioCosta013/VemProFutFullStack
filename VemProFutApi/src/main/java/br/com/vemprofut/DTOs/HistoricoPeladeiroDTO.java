package br.com.vemprofut.DTOs;

public record HistoricoPeladeiroDTO(
    Long id,
    Integer golsDoPeladeiro,
    Double notaPeladeiro,
    Integer partidasJogadas,
    Integer partidasGanhas
) { }
