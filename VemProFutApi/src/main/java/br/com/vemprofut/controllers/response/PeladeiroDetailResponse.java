package br.com.vemprofut.controllers.response;

import br.com.vemprofut.services.implementacao.CartoesService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public record PeladeiroDetailResponse(
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
        @JsonProperty("whatsapp")
        String whatsapp,
        @JsonProperty("peDominante")
        String peDominante,
        @JsonProperty("numeroCartoes")
        CartoesResumoResponseDTO cartoes
) {

}
