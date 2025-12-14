package br.com.vemprofut.controllers.response;

import br.com.vemprofut.models.enuns.TipoCartao;

public record CartoesResponseDTO(
    Long id, Long partida, Long peladeiro, Long fut, TipoCartao tipoCartao) {}
