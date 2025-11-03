package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.models.DTOs.GolsPartidaDTO;
import br.com.vemprofut.services.IGolsPartidaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GolsPartidaService implements IGolsPartidaService {
    @Override
    public GolsPartidaDTO create(GolsPartidaDTO dto) {
        return null;
    }

    @Override
    public GolsPartidaDTO findById(Long id) {
        return null;
    }

    @Override
    public List<GolsPartidaDTO> findAll() {
        return List.of();
    }

    @Override
    public GolsPartidaDTO update(Long id, GolsPartidaDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
