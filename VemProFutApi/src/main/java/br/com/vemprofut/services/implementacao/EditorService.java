package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.services.IEditorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorService implements IEditorService {
    @Override
    public EditorDTO create(EditorDTO dto) {
        return null;
    }

    @Override
    public EditorDTO findById(Long id) {
        return null;
    }

    @Override
    public List<EditorDTO> findAll() {
        return List.of();
    }

    @Override
    public EditorDTO update(Long id, EditorDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
