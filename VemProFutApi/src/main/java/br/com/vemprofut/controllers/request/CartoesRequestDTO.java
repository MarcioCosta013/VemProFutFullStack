package br.com.vemprofut.controllers.request;

import br.com.vemprofut.models.enuns.TipoCartao;

public record CartoesRequestDTO(Long peladeiroId, Long futId, TipoCartao tipoCartao) {}
