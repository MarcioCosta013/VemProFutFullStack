package br.com.vemprofut.controllers.response;

import br.com.vemprofut.models.enuns.TipoCartao;

public record CartoesResponseDTO(
    Long id, Long partidaId, Long peladeiroId, Long futId, TipoCartao tipoCartao) {}
