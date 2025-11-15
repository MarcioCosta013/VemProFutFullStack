package br.com.vemprofut.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record SaveFutRequestDTO(
        @NotBlank String nome,
        @PositiveOrZero Integer jogadoresPorTime,
        @PositiveOrZero Integer tempoMaxPartida,
        @PositiveOrZero Integer maxGolsVitoria,
        @PositiveOrZero Long administradorPeladeiroId
) { }
