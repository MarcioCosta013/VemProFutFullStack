package br.com.vemprofut.services.query;

import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.EditorModel;

public interface IEditorQueryService {

  void verityEditorExist(EditorDTO dto);

  EditorModel verityEditorIdExistReturn(Long id);

  void verityEditorIdExist(Long id);

  void verityFutExist(FutDTO futDTO);
}
