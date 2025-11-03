package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.PeladeiroMapper;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.IPeladeiroService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeladeiroService implements IPeladeiroService {

    @Autowired
    private IPeladeiroQueryService queryService;

    @Autowired
    private PeladeiroRepository repository;

    @Autowired
    private PeladeiroMapper peladeiroMapper;

    @Override
    public PeladeiroDTO create(PeladeiroDTO dto) {
        queryService.verifyEmail(dto.email());
        PeladeiroModel peladeiroModel = peladeiroMapper.toModel(dto);
        PeladeiroModel peladeiroSalvo = repository.save(peladeiroModel);
        return peladeiroMapper.toDTO(peladeiroSalvo);
    }

    @Override
    public PeladeiroDTO update(Long id, PeladeiroDTO dto) {
        return null;
    }

    @Override
    public PeladeiroDTO findById(Long id) {
        return null;
    }

    @Override
    public List<PeladeiroDTO> findAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
