package br.com.vemprofut.models.DTOs;

import java.util.List;

public record CartoesDTO(
        Long id,
        Integer cartoes_azul,
        Integer cartoes_amarelos,
        Integer cartoes_vermelhos,
        List<Long> partidasIds,
        List<Long> peladeirosIds
) { }
