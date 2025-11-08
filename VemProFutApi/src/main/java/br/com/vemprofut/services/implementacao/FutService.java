package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.IFutMapper;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.services.IFutService;
import br.com.vemprofut.services.query.IFutQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FutService implements IFutService {

    @Autowired
    private IFutQueryService queryService;

    @Autowired
    private IFutMapper mapper;

    @Autowired
    private FutRepository repository;

    @Autowired
    private PartidasService partidasService;

    @Override
    public FutDTO create(FutDTO dto) {
        queryService.verifyFutExist(dto.id()); //depois de criar os responce e request DTO avaliar de vai tirar essa verificação.
        queryService.verifyNomeFutExist(dto.nome());

        FutModel model = mapper.toModel(dto);
        FutModel saved = repository.save(model);

        return mapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FutDTO findById(Long id) {
        var futModel= queryService.verifyFutExistRetorn(id);
        return mapper.toDTO(futModel);
    }

    @Override
    public FutModel findByIdModel(Long id) {
        return queryService.verifyFutExistRetorn(id);
    }

    @Override
    public FutDTO findByNome(String nome) {
        var futModel = queryService.verifyNomeFutExistRetorn(nome);
        return mapper.toDTO(futModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FutDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public FutDTO update(Long id, FutDTO dto) {
        queryService.verifyNomeFutExist(dto.nome());
        var retorno = queryService.verifyFutExistRetorn(id);

        retorno.setNome(dto.nome());
        retorno.setJogadoresPorTime(dto.jogadoresPorTime());
        retorno.setTempoMaxPartida(dto.tempoMaxPartida());
        retorno.setMaxGolsVitoria(dto.maxGolsVitoria());

        return mapper.toDTO(repository.save(retorno));
    }

    @Override
    public void delete(Long id) {
        queryService.verifyFutExist(id);
        repository.deleteById(id);
    }

    @Override
    public void criarPartida(Boolean jogadoresReservas, FutModel futModel) {

        partidasService.create(jogadoresReservas, futModel);
    }

    @Override
    public void addPeladeiro(FutDTO futDTO, PeladeiroDTO peladeiroDTO) {
        //TODO:Verity
        futDTO.peladeiros().add(peladeiroDTO.id());
    }

    @Override
    public void addCartoes(FutDTO futDTO, CartoesDTO cartoesDTO) {
        //TODO:Verity
        futDTO.cartoes().add(cartoesDTO.id());
    }
}
