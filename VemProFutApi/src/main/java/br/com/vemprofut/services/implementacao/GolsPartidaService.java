package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.GolsPartidaMapper;
import br.com.vemprofut.models.DTOs.GolsPartidaDTO;
import br.com.vemprofut.repositories.GolsPartidaRepository;
import br.com.vemprofut.services.IGolsPartidaService;
import br.com.vemprofut.services.query.IGolsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GolsPartidaService implements IGolsPartidaService {
    @Autowired
    IGolsQueryService queryService;

    @Autowired
    GolsPartidaRepository repository;

    @Autowired
    GolsPartidaMapper mapper;

    @Override
    public void create(GolsPartidaDTO dto) {
        repository.save(mapper.toModel(dto));
    }

    @Override
    public GolsPartidaDTO findById(Long id) {
        return mapper.toDTO(queryService.verifyGolExistWithRetorn(id));
    }

    @Override
    public List<GolsPartidaDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<GolsPartidaDTO> findAllPeladeiro( Long id) {
        return repository.findByPeladeiro(id)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<GolsPartidaDTO> findAllPartida( Long id) {
        return repository.findByPartida(id)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        queryService.verifyGolExist(id);
        repository.deleteById(id);
    }
}
