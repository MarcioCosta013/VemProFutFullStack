package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.IHistoricoPeladeiroMapper;
import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import br.com.vemprofut.repositories.HistoricoPeladeiroRepository;
import br.com.vemprofut.services.IHistoricoPeladeiroService;
import br.com.vemprofut.services.query.IHistoricoPeladeiroQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoricoPeladeiroService implements IHistoricoPeladeiroService {

    @Autowired
    private IHistoricoPeladeiroQueryService queryService;

    @Autowired
    private HistoricoPeladeiroRepository repository;

    @Autowired
    private IHistoricoPeladeiroMapper mapper;

    @Override
    @Transactional
    public HistoricoPeladeiroDTO create() {
        HistoricoPeladeiroModel historico = new HistoricoPeladeiroModel();
        return mapper.toDTO(repository.save(historico));
    }

    @Override
    @Transactional(readOnly = true)
    public HistoricoPeladeiroDTO findById(Long id) {

        return mapper.toDTO(queryService.verityHistoricoPeladeiroExistReturn(id));
    }

    @Override
    @Transactional(readOnly = true)
    public HistoricoPeladeiroModel findByIdModel(Long id) {

        return queryService.verityHistoricoPeladeiroExistReturn(id);
    }


    @Override
    public HistoricoPeladeiroDTO update(Long id, HistoricoPeladeiroDTO dto) {
        HistoricoPeladeiroModel historico = queryService.verityHistoricoPeladeiroExistReturn(id);

        historico.setGolsDoPeladeiro(dto.golsDoPeladeiro());
        historico.setNotaPeladeiro(dto.notaPeladeiro());
        historico.setPartidasJogadas(dto.partidasJogadas());
        historico.setPartidasGanhas(dto.partidasGanhas());

        return mapper.toDTO(repository.save(historico));
    }

    @Override
    public void delete(Long id) {
        queryService.verityHistoricoPeladeiroExist(id);
        repository.deleteById(id);
    }
}
