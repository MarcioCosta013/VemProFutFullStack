package br.com.vemprofut.DTOs;

import java.util.List;

public record PeladeiroDTO (
        Long id,
        String nome,
        String apelido,
        String descricao,
        String peDominante,
        String whatsapp,
        Long historicoPeladeiro,
        List<Long> partidasIDs,
        List<Long> futsIDs
){ }
