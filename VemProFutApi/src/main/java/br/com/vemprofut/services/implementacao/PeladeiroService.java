package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.request.UpdatePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.UpdatePeladeiroResponseDTO;
import br.com.vemprofut.mappers.IHistoricoPeladeiroMapper;
import br.com.vemprofut.mappers.IPeladeiroMapper;
import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
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
    private IPeladeiroMapper IPeladeiroMapper;

    @Autowired
    private IHistoricoPeladeiroMapper historicoMapper;

    @Autowired
    private IHistoricoPeladeiroService historicoPeladeiroService;

    @Override
    @Transactional
    public SavePeladeiroResponseDTO create(SavePeladeiroRequestDTO dto) {
        queryService.verifyEmail(dto.email());
        PeladeiroModel peladeiroModel = IPeladeiroMapper.saveRequestToModel(dto);
        PeladeiroModel peladeiroSalvo = repository.save(peladeiroModel);

        HistoricoPeladeiroDTO historico = historicoPeladeiroService.create();
        peladeiroSalvo.setHistoricoPeladeiro(historicoMapper.toModel(historico));

        return IPeladeiroMapper.modelToSaveResponse(repository.save(peladeiroSalvo));
    }

    @Override
    @Transactional
    public UpdatePeladeiroResponseDTO update(Long id, UpdatePeladeiroRequestDTO dto) {
        var peladeiroModel = queryService.verifyPeladeiroExist(id);

        peladeiroModel.setNome(dto.nome());
        peladeiroModel.setEmail(dto.email());
        peladeiroModel.setApelido(dto.apelido());
        peladeiroModel.setDescricao(dto.descricao());
        peladeiroModel.setWhatsapp(dto.whatsapp());
        peladeiroModel.setPeDominante(dto.peDominante());

        return IPeladeiroMapper.modelToUpdateResponse(repository.save(peladeiroModel));
    }

    @Override
    @Transactional(readOnly = true)
    public PeladeiroDetailResponse findById(Long id) {
        return IPeladeiroMapper.modelToDetailsDTO(queryService.verifyPeladeiroExist(id));
    }

    @Override
    @Transactional(readOnly = true)
    public PeladeiroModel findByIdModel(Long id) {
        return queryService.verifyPeladeiroExist(id);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<PeladeiroDTO> findAll() {
//
//        return repository.findAll()
//                .stream()
//                .map(IPeladeiroMapper::toDTO)
//                .toList();
//    }

    @Override
    @Transactional
    public void delete(Long id) {
        queryService.verifyPeladeiroExist(id);
        repository.deleteById(id);
    }
}
