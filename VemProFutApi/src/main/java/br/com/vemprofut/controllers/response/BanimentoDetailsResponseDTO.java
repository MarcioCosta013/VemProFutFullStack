package br.com.vemprofut.controllers.response;

import java.time.LocalDate;

public record BanimentoDetailsResponseDTO(
    Long id,
    String motivo,
    LocalDate dataBanimento,
    LocalDate dataFimBanimento,
    Long peladeiro,
    Long fut) {}
