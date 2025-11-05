package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.services.IPartidasService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidasService implements IPartidasService {
    @Override
    public PartidasDTO create(PartidasDTO dto) {

        return null;
    }

    @Override
    public PartidasDTO findById(Long id) {

        return null;
    }

    @Override
    public List<PartidasDTO> findAll() {

        return List.of();
    }

    @Override
    public PartidasDTO update(Long id, PartidasDTO dto) {

        return null;
    }
}
