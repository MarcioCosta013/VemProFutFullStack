package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.services.IHistoricoPeladeiroService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoPeladeiroService implements IHistoricoPeladeiroService {
    @Override
    public HistoricoPeladeiroDTO create(HistoricoPeladeiroDTO dto) {
        return null;
    }

    @Override
    public HistoricoPeladeiroDTO findById(Long id) {
        return null;
    }

    @Override
    public List<HistoricoPeladeiroDTO> findAll() {
        return List.of();
    }

    @Override
    public HistoricoPeladeiroDTO update(Long id, HistoricoPeladeiroDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
