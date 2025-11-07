package br.com.vemprofut.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SavePeladeiroResponseDTO(
        @JsonProperty("id")
        Long id,
        @JsonProperty("nome")
        String nome,
        @JsonProperty("email")
        String email,
        @JsonProperty("apelido")
        String apelido,
        @JsonProperty("descricao")
        String descricao,
        @JsonProperty("peDominante")
        String peDominante,
        @JsonProperty("whatsapp")
        String whatsapp,
        @JsonProperty("historicoPeladeiro")
        Long historicoPeladeiro,
        @JsonProperty("partidasIDs")
        List<Long> partidasIDs,
        @JsonProperty("futsIDs")
        List<Long> futsIDs,
        @JsonProperty("cartoes")
        List<Long> cartoes
) { }
