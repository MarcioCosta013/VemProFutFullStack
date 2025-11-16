package br.com.vemprofut.controllers.response;

import java.util.List;

public record SavePartidasResponseDTO(
    Long id,
    Boolean reservas,
    Long futId,
    List<CartoesResponseDTO> cartoes,
    List<GolsPartidaResponseDTO> golsPartida,
    List<PeladeiroResponseDTO> peladeiros) {}
