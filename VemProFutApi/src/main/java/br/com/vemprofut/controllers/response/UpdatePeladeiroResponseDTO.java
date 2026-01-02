package br.com.vemprofut.controllers.response;

import br.com.vemprofut.models.enuns.PeDominante;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdatePeladeiroResponseDTO(
    @JsonProperty("nome") String nome,
    @JsonProperty("email") String email,
    @JsonProperty("apelido") String apelido,
    @JsonProperty("descricao") String descricao,
    @JsonProperty("whatsapp") String whatsapp,
    @JsonProperty("peDominante") PeDominante peDominante,
    @JsonProperty("fotoURL") String fotoUrl) {}
