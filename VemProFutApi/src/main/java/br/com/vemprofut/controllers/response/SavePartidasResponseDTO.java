package br.com.vemprofut.controllers.response;

import java.util.List;

public record SavePartidasResponseDTO(
        Long id,
        Boolean reservas,
        Long futId,
        List<Long> cartoes,
        List<Long> golsPartida,
        List<Long> peladeiros
) {}
