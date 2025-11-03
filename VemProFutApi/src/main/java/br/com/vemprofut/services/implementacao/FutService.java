package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.services.IFutService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FutService implements IFutService {
    @Override
    public FutDTO create(FutDTO dto) {
        return null;
    }

    @Override
    public FutDTO findById(Long id) {
        return null;
    }

    @Override
    public List<FutDTO> findAll() {
        return List.of();
    }

    @Override
    public FutDTO update(Long id, FutDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
