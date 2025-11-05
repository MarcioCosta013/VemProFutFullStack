package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.PartidasMapper;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PartidasRepository;
import br.com.vemprofut.services.IPartidasService;
import br.com.vemprofut.services.query.implementacao.PartidasQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidasService implements IPartidasService {

    @Autowired
    private PartidasMapper mapperPartidas;

    @Autowired
    private PartidasRepository repository;

    @Autowired
    private PartidasQueryService queryService;

    @Autowired
    private GolsPartidaService golsService;

    @Autowired
    private CartoesService cartoesService;

    @Override
    public PartidasDTO create(PartidasDTO dto) {
        PartidasModel partidasModel = mapperPartidas.toModel(dto);
        //TODO: alterar todas as classes de Gols e Cartao para se adaptarem a essa.
        //TODO: criar golsPartida e CartoesPartida daqui.
        //TODO: criar ou o metodo em fut para criar uma partida ou pegar o id de Fut e colocar aqui
        return null;
    }

    @Override
    public PartidasDTO findById(Long id) {

        return null;
    }

    @Override
    public void addGolPartida(GolsPartidaModel gols) {

    }

    @Override
    public void addPeladeiros(PeladeiroModel peladeiro) {

    }

    @Override
    public void addCartoes(CartoesModel cartoes) {

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
