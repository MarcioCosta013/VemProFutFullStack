package br.com.vemprofut.models.DTOs;

import java.util.List;

public record PeladeiroDTO (
        Long id,
        String nome,
        String email,
        String apelido,
        String descricao,
        String peDominante,
        String whatsapp,
        Long historicoPeladeiro,
        List<Long> partidasIDs,
        List<Long> futsIDs
){ }
