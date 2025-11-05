package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.models.DTOs.HistoricoFutDTO;
import br.com.vemprofut.services.IHistoricoFutService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoFutService implements IHistoricoFutService {
    @Override
    public HistoricoFutDTO create(HistoricoFutDTO dto) {
        //Todo
        return null;
    }

    @Override
    public HistoricoFutDTO findById(Long id) {
        //Todo
        return null;
    }

    @Override
    public List<HistoricoFutDTO> findAll() {
        //Todo
        return List.of();
    }

    @Override
    public HistoricoFutDTO update(Long id, HistoricoFutDTO dto) {
        //Todo
        return null;
    }

    @Override
    public void delete(Long id) {
        //Todo
    }
}
