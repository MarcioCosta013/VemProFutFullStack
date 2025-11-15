package br.com.vemprofut.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PeladeiroDetailResponse(
    @JsonProperty("id") Long id,
    @JsonProperty("nome") String nome,
    @JsonProperty("email") String email,
    @JsonProperty("apelido") String apelido,
    @JsonProperty("descricao") String descricao,
    @JsonProperty("whatsapp") String whatsapp,
    @JsonProperty("peDominante") String peDominante,
    @JsonProperty("numeroCartoes") CartoesResumoResponseDTO cartoes) {}
