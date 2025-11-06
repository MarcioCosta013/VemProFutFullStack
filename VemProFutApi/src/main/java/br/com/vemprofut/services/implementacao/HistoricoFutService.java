package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.HistoricoFutMapper;
import br.com.vemprofut.models.DTOs.HistoricoFutDTO;
import br.com.vemprofut.models.HistoricoFutModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.HisotricoFutRepository;
import br.com.vemprofut.services.IHistoricoFutService;
import br.com.vemprofut.services.query.IHistoricoFutQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoFutService implements IHistoricoFutService {

    @Autowired
    private HistoricoFutMapper mapper;

    @Autowired
    private HisotricoFutRepository repository;

    @Autowired
    private IHistoricoFutQueryService queryService;
    @Override
    @Transactional
    public HistoricoFutDTO create() {
        HistoricoFutModel futModel = new HistoricoFutModel();
        return mapper.toDTO(repository.save(futModel));
    }

    @Override
    @Transactional(readOnly = true)
    public HistoricoFutDTO findById(Long id) {
        return mapper.toDTO(queryService.verityHistoricoFutExistRetorn(id));
    }

    @Override
    public void updateTimeMaisVitorias(Long id, List<PeladeiroModel> time) {
        HistoricoFutModel model = queryService.verityHistoricoFutExistRetorn(id);
        model.setTimeMaisVitorias(time.stream()
                .map(PeladeiroModel::getNomePeladeiro)
                .collect(Collectors.joining(", ")));

        repository.save(model);
    }

    @Override
    public void addPartidasJogadas(Long id, Integer numero) {
        HistoricoFutModel model = queryService.verityHistoricoFutExistRetorn(id);

        model.setTotalPartidasJogadas(model.getTotalPartidasJogadas() + numero);

        repository.save(model);
    }

    @Override
    public void addGolsTotal(Long id, Integer numero) {
        HistoricoFutModel model = queryService.verityHistoricoFutExistRetorn(id);

        model.setGolsTotalFut(model.getGolsTotalFut() + numero);

        repository.save(model);
    }

    @Override
    public void delete(Long id) {
        queryService.verityHistoricoFutExist(id);
        repository.deleteById(id);
    }
}
