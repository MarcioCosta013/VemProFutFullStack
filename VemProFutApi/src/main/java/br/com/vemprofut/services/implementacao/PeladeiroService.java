package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.HistoricoPeladeiroMapper;
import br.com.vemprofut.mappers.PeladeiroMapper;
import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.IHistoricoPeladeiroService;
import br.com.vemprofut.services.IPeladeiroService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeladeiroService implements IPeladeiroService {

    @Autowired
    private IPeladeiroQueryService queryService;

    @Autowired
    private PeladeiroRepository repository;

    @Autowired
    private PeladeiroMapper peladeiroMapper;

    @Autowired
    private HistoricoPeladeiroMapper historicoMapper;

    @Autowired
    private IHistoricoPeladeiroService historicoPeladeiroService;

    @Override
    @Transactional
    public PeladeiroDTO create(PeladeiroDTO dto) {
        queryService.verifyEmail(dto.email());
        PeladeiroModel peladeiroModel = peladeiroMapper.toModel(dto);
        PeladeiroModel peladeiroSalvo = repository.save(peladeiroModel);

        HistoricoPeladeiroDTO historico = historicoPeladeiroService.create();
        peladeiroSalvo.setHistoricoPeladeiroModel(historicoMapper.toModel(historico));

        return peladeiroMapper.toDTO(repository.save(peladeiroSalvo));
    }

    @Override
    @Transactional
    public PeladeiroDTO update(Long id, PeladeiroDTO dto) {
        var peladeiroModel = queryService.verifyPeladeiroExist(id);

        peladeiroModel.setNomePeladeiro(dto.nome());
        peladeiroModel.setEmailPeladeiro(dto.email());
        peladeiroModel.setApelidoPeladeiro(dto.apelido());
        peladeiroModel.setDescricaoPeladeiro(dto.descricao());
        peladeiroModel.setWhatsappPeladeiro(dto.whatsapp());
        peladeiroModel.setPeDominante(dto.peDominante());

        return peladeiroMapper.toDTO(repository.save(peladeiroModel));
    }

    @Override
    @Transactional(readOnly = true)
    public PeladeiroDTO findById(Long id) {
        return peladeiroMapper.toDTO(queryService.verifyPeladeiroExist(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeladeiroDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(peladeiroMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        queryService.verifyPeladeiroExist(id);
        repository.deleteById(id);
    }
}
