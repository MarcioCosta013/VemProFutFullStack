package br.com.vemprofut.models.DTOs;

import br.com.vemprofut.models.CartoesModel;

import java.util.List;

public record PartidasDTO(
        Long id,
        Boolean reservas,
        Long idFut,
        List<Long> golsPartida,
        List<Long> peladeiros,
        List<Long> cartoes
) { }
