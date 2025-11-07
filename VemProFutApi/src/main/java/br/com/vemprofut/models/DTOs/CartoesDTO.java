package br.com.vemprofut.models.DTOs;

import br.com.vemprofut.models.enuns.TipoCartao;

import java.util.List;

public record CartoesDTO(
        Long id,
        Long partidaId,
        Long peladeiroId,
        Long futId,
        TipoCartao tipoCartao
) { }
