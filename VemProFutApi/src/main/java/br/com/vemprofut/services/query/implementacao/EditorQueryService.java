package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.EditorInUseException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.mappers.IEditorMapper;
import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.EditorModel;
import br.com.vemprofut.repositories.EditorRepository;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.services.query.IEditorQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorQueryService implements IEditorQueryService {

    @Autowired
    private EditorRepository repository;

    @Autowired
    private IEditorMapper mapper;

    @Autowired
    private FutRepository futRepository;

    @Override
    public void verityEditorExist(EditorDTO dto) {
        EditorModel model = mapper.toModel(dto);

        if(repository.findByPeladeiroAndFut(model.getPeladeiroIdEditor(), model.getFutId())){
            throw new EditorInUseException("Editor já cadastrado!");
        }
    }

    @Override
    public EditorModel verityEditorIdExistReturn(Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new EditorInUseException("Editor já cadastrado!")
        );
    }

    @Override
    public void verityEditorIdExist(Long id) {
        if(!repository.existsById(id)){
            throw new NotFoundException("Editor de ID: "+ id +"não foi encontrado!");
        }
    }

    @Override
    public void verityFutExist(FutDTO futDTO) {

        if(!futRepository.existsById(futDTO.id())){
            throw new NotFoundException("Não foi possivel encopntrar o Fut de ID: " + futDTO.id());
        }
    }
}
