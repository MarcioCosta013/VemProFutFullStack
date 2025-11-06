package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.GolsPartidaMapper;
import br.com.vemprofut.mappers.PartidasMapper;
import br.com.vemprofut.mappers.PeladeiroMapper;
import br.com.vemprofut.models.DTOs.GolsPartidaDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.GolsPartidaRepository;
import br.com.vemprofut.services.IGolsPartidaService;
import br.com.vemprofut.services.query.IGolsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GolsPartidaService implements IGolsPartidaService {
    @Autowired
    private IGolsQueryService queryService;

    @Autowired
    private GolsPartidaRepository repository;

    @Autowired
    private GolsPartidaMapper mapper;

    @Autowired
    private PeladeiroMapper peladeiroMapper;

    @Autowired
    PartidasMapper partidasMapper;

    @Override
    @Transactional
    public GolsPartidaModel create(PeladeiroDTO peladeiroDTO, PartidasDTO partidasDTO) {
        PeladeiroModel peladeiroModel = peladeiroMapper.toModel(peladeiroDTO);
        PartidasModel partidasModel = partidasMapper.toModel(partidasDTO);

        GolsPartidaModel golsPartidaModel = new GolsPartidaModel(peladeiroModel, partidasModel);

        return repository.save(golsPartidaModel);
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
