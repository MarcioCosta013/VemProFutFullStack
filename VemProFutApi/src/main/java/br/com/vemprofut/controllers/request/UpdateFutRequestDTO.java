package br.com.vemprofut.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateFutRequestDTO(
        @PositiveOrZero Integer jogadoresPorTime,
        @PositiveOrZero Integer tempoMaxPartida,
        @PositiveOrZero Integer maxGolsVitoria
) { }
