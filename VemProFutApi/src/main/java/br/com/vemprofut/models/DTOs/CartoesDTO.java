package br.com.vemprofut.models.DTOs;

import br.com.vemprofut.models.enuns.TipoCartao;

public record CartoesDTO(
    Long id, Long partidaId, Long peladeiroId, Long futId, TipoCartao tipoCartao) {}
