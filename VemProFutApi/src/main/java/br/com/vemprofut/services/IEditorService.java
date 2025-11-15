package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.EditorModel;
import java.util.List;

public interface IEditorService {

  EditorDTO create(EditorDTO dto);

  EditorDTO findById(Long id);

  EditorModel findByIdModel(Long id);

  List<EditorDTO> findAll(FutDTO futDTO);

  void delete(Long id);
}
