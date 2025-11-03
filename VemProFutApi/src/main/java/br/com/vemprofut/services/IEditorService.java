package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.EditorDTO;

import java.util.List;

public interface IEditorService {

    EditorDTO create(EditorDTO dto);

    EditorDTO findById(Long id);

    List<EditorDTO> findAll();

    EditorDTO update(Long id, EditorDTO dto);

    void delete(Long id);
}
