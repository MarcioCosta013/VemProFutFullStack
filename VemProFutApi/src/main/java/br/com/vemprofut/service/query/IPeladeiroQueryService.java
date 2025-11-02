package br.com.vemprofut.service;

import br.com.vemprofut.DTOs.PeladeiroDTO;
import br.com.vemprofut.service.implementacao.PeladeiroService;

public interface IPeladeiroService {

    PeladeiroDTO salvar(PeladeiroDTO dto);

    PeladeiroDTO alterar(PeladeiroDTO dto);

    void apagar(Long id);
}
