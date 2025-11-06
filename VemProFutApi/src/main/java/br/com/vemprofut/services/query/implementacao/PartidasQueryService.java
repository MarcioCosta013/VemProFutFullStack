package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.CartoesRepository;
import br.com.vemprofut.repositories.GolsPartidaRepository;
import br.com.vemprofut.repositories.PartidasRepository;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.query.IPartidasQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidasQueryService implements IPartidasQueryService {
    @Autowired
    private PartidasRepository repository;

    @Autowired
    private GolsPartidaRepository golsRepository;

    @Autowired
    private CartoesRepository cartoesRepository;

    @Autowired
    private PeladeiroRepository peladeiroRepository;

    @Override
    public PartidasModel verifyPartidaExistWithRetorn(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado a Partida de id " + id)
        );
    }

}
