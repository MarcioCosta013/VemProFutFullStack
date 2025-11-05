package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.FutMapper;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.HistoricoFutModel;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.services.IFutService;
import br.com.vemprofut.services.query.IFutQueryService;
import br.com.vemprofut.services.query.implementacao.FutQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FutService implements IFutService {

    @Autowired
    IFutQueryService queryService;

    @Autowired
    FutMapper mapper;

    @Autowired
    FutRepository repository;

    @Override
    public FutDTO create(FutDTO dto) {
        queryService.verifyFutExist(dto.id());
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

        retorno.setNomeFut(dto.nome());
        retorno.setJogadoresPorTime(dto.jogadoresPorTime());
        retorno.setTempoMaxPartida(dto.tempoMaxPartida());
        retorno.setMaxGolsVitoria(dto.maxGolsPartida());

        //Todo: historicopeladeiro id
        //Todo: add adm
        //Todo: lista de editores
        //Todo: lista de peladeiros geral
        //Todo: lista de cartoes
        return mapper.toDTO(repository.save(retorno));
    }

    @Override
    public void delete(Long id) {
        queryService.verifyFutExist(id);
        repository.deleteById(id);
    }
}
