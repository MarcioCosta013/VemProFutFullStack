package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.EditorModel;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IEditorService {

  CompletableFuture<EditorModel> create(EditorModel dto);

  CompletableFuture<EditorDTO> findById(Long id);

  CompletableFuture<EditorModel> findByIdModel(Long id);

  CompletableFuture<List<EditorDTO>> findAll(FutDTO futDTO);

  CompletableFuture<Void> delete(Long id);
}
