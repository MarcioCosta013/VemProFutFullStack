package br.com.vemprofut.controllers.request;

import java.time.LocalDate;

public record SaveBanimentoRequestDTO(
    String motivo,
    LocalDate dataBaninimento,
    LocalDate dataFimBanimento,
    Long peladeiro,
    Long fut) {}
