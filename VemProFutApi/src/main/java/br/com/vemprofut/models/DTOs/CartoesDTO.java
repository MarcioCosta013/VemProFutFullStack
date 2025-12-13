package br.com.vemprofut.models.DTOs;

import br.com.vemprofut.models.enuns.TipoCartao;

public record CartoesDTO(Long id, Long partida, Long peladeiro, Long fut, TipoCartao tipoCartao) {}
