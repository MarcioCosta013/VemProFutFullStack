package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.IGolsPartidaMapper;
import br.com.vemprofut.mappers.IPartidasMapper;
import br.com.vemprofut.mappers.IPeladeiroMapper;
import br.com.vemprofut.models.DTOs.GolsPartidaDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.GolsPartidaRepository;
import br.com.vemprofut.services.IGolsPartidaService;
import br.com.vemprofut.services.query.IGolsQueryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GolsPartidaService implements IGolsPartidaService {
  @Autowired private IGolsQueryService queryService;

  @Autowired private GolsPartidaRepository repository;

  @Autowired private IGolsPartidaMapper mapper;

  @Autowired private IPeladeiroMapper IPeladeiroMapper;

  @Autowired IPartidasMapper IPartidasMapper;

  @Override
  @Transactional
  public GolsPartidaModel create(PeladeiroDTO peladeiroDTO, PartidasDTO partidasDTO) {
    PeladeiroModel peladeiroModel = IPeladeiroMapper.toModel(peladeiroDTO);
    PartidasModel partidasModel = IPartidasMapper.toModel(partidasDTO);

    GolsPartidaModel golsPartidaModel = new GolsPartidaModel(peladeiroModel, partidasModel);

    return repository.save(golsPartidaModel);
  }

  @Override
  public GolsPartidaDTO findById(Long id) {

    return mapper.toDTO(queryService.verifyGolExistWithRetorn(id));
  }

  @Override
  public GolsPartidaModel findByIdModel(Long id) {

    return queryService.verifyGolExistWithRetorn(id);
  }

  @Override
  public List<GolsPartidaDTO> findAll() {

    return repository.findAll().stream().map(mapper::toDTO).toList();
  }

  @Override
  public List<GolsPartidaDTO> findAllPeladeiro(Long id) {
    return repository.findByPeladeiro(id).stream().map(mapper::toDTO).toList();
  }

  @Override
  public List<GolsPartidaDTO> findAllPartida(Long id) {
    return repository.findByPartida(id).stream().map(mapper::toDTO).toList();
  }

  @Override
  public void delete(Long id) {
    queryService.verifyGolExist(id);
    repository.deleteById(id);
  }
}
