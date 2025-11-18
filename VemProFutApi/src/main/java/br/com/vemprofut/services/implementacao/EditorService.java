package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.IEditorMapper;
import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.EditorModel;
import br.com.vemprofut.repositories.EditorRepository;
import br.com.vemprofut.services.IEditorService;
import br.com.vemprofut.services.query.IEditorQueryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorService implements IEditorService {

  @Autowired private IEditorMapper mapper;

  @Autowired private IEditorQueryService queryService;

  @Autowired private EditorRepository repository;

  @Override
  public EditorModel create(EditorModel model) {
    queryService.verityEditorExist(model);
    return repository.save(model);
  }

  @Override
  @Transactional(readOnly = true)
  public EditorDTO findById(Long id) {
    return mapper.toDTO(queryService.verityEditorIdExistReturn(id));
  }

  @Override
  @Transactional(readOnly = true)
  public EditorModel findByIdModel(Long id) {
    return queryService.verityEditorIdExistReturn(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<EditorDTO> findAll(FutDTO futDTO) {
    queryService.verityFutExist(futDTO);

    return repository.findByFutId(futDTO.id()).stream().map(mapper::toDTO).toList();
  }

  @Override
  public void delete(Long id) {
    queryService.verityEditorIdExist(id);
    repository.deleteById(id);
  }
}
