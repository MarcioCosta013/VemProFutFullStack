package br.com.vemprofut.controllers.request;

import java.util.List;

public record SavePartidaRequestDTO(
        Boolean reservas,
        Long futId) {}
