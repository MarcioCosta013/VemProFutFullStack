package br.com.vemprofut.controllers.request;

import java.util.List;

public record SavePartidaRequestDTO(
    Boolean reservas,
    Long futId,
    List<CartoesRequestDTO> cartoes,
    List<GolsPartidaRequestDTO> gols,
    List<PeladeiroRequestDTO> peladeiros) {}
