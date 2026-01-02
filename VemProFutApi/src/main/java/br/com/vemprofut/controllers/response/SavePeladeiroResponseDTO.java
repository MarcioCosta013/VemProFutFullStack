package br.com.vemprofut.controllers.response;

import br.com.vemprofut.models.enuns.PeDominante;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record SavePeladeiroResponseDTO(
    @JsonProperty("id") Long id,
    @JsonProperty("nome") String nome,
    @JsonProperty("email") String email,
    @JsonProperty("apelido") String apelido,
    @JsonProperty("descricao") String descricao,
    @JsonProperty("peDominante") PeDominante peDominante,
    @JsonProperty("whatsapp") String whatsapp,
    @JsonProperty("historicoPeladeiro") Long historicoPeladeiro,
    @JsonProperty("fotoUrl") String fotoUrl,
    @JsonProperty("partidasIDs") List<Long> partidas,
    @JsonProperty("futsIDs") List<Long> futs,
    @JsonProperty("cartoes") List<Long> cartoes) {}
