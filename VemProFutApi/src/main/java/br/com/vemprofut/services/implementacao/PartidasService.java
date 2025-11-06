package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.ICartoesMapper;
import br.com.vemprofut.mappers.IPartidasMapper;
import br.com.vemprofut.mappers.IPeladeiroMapper;
import br.com.vemprofut.models.*;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.repositories.PartidasRepository;
import br.com.vemprofut.services.IPartidasService;
import br.com.vemprofut.services.query.IPartidasQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidasService implements IPartidasService {

    @Autowired
    private IPartidasMapper mapperPartidas;

    @Autowired
    private PartidasRepository repository;

    @Autowired
    private IPartidasQueryService queryService;

    @Autowired
    private GolsPartidaService golsService;

    @Autowired
    private CartoesService cartoesService;

    @Autowired
    private PeladeiroService peladeiroService;

    @Autowired
    private IPeladeiroMapper IPeladeiroMapper;

    @Autowired
    private ICartoesMapper ICartoesMapper;

    @Override
    @Transactional
    public PartidasDTO create(Boolean jogadoresReservas, FutModel futModel) {
        PartidasModel partidasModel = new PartidasModel(jogadoresReservas, futModel);
        return mapperPartidas.toDTO(repository.save(partidasModel));
    }

    @Override
    @Transactional(readOnly = true)
    public PartidasDTO findById(Long id) {
        return mapperPartidas.toDTO(queryService.verifyPartidaExistWithRetorn(id));
    }

    @Override
    @Transactional
    public void addGols(PeladeiroDTO peladeiroDTO, PartidasDTO partidasDTO) {
         GolsPartidaModel gol = golsService.create(peladeiroDTO, partidasDTO);

         PartidasModel partida = mapperPartidas.toModel(partidasDTO);
         partida.getGolsPartida().add(gol);

         repository.save(partida);
    }

    @Override
    @Transactional
    public void addPeladeiros(PeladeiroDTO peladeiro, PartidasDTO partidasDTO) {

        PeladeiroDTO peladeiroDTO = peladeiroService.findById(peladeiro.id());

        PartidasModel partida = mapperPartidas.toModel(partidasDTO);
        partida.getPeladeiros().add(IPeladeiroMapper.toModel(peladeiroDTO));

        repository.save(partida);
    }

    @Override
    @Transactional
    public void addCartoes(CartoesDTO cartoes, PartidasDTO partidasDTO) {

        CartoesDTO cartoesDTO = cartoesService.findById(cartoes.id());

        PartidasModel partida = mapperPartidas.toModel(partidasDTO);
        partida.getCartoes().add(ICartoesMapper.toModel(cartoesDTO));

        repository.save(partida);
    }
}
