package br.com.vemprofut.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SaveFutResponseDTO(
        @JsonProperty("id")
        Long id,
        @JsonProperty("nome")
        String nome,
        @JsonProperty("jogadoresPorTime")
        Integer jogadoresPorTime,
        @JsonProperty("tempoMaxPartida")
        Integer tempoMaxPartida,
        @JsonProperty("maxGolsVitoria")
        Integer maxGolsVitoria,
        @JsonProperty("historicoFutId")
        Long historicoFutId,
        @JsonProperty("administradorPeladeiro")
        Long administradorPeladeiro
) { }
