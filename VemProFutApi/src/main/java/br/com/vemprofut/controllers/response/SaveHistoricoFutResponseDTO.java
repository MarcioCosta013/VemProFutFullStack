package br.com.vemprofut.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SaveHistoricoFutResponseDTO(
        @JsonProperty("id")
        Long id,
        @JsonProperty("golsTotal")
        Integer golsTotal,
        @JsonProperty("totalPartidas")
        Integer totalPartidas,
        @JsonProperty("timeMaisVitorias")
        String timeMaisVitorias
) { }
