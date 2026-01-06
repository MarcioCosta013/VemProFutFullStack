package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.mappers.IEditorMapper;
import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.EditorModel;
import br.com.vemprofut.repositories.EditorRepository;
import br.com.vemprofut.services.IEditorService;
import br.com.vemprofut.services.query.IEditorQueryService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorService implements IEditorService {

  @Autowired private IEditorMapper mapper;

  @Autowired private IEditorQueryService queryService;

  @Autowired private EditorRepository repository;

  @Override
  @Async("defaultExecutor")
  @Transactional
  public CompletableFuture<EditorModel> create(EditorModel model) {
    //    queryService.verityEditorExist(model); ---> nao async
    //    return CompletableFuture.completedFuture(repository.save(model));

    // Async
    return CompletableFuture.supplyAsync(
        () -> {
          queryService.verityEditorExist(model);
          return repository.save(model);
        });
  }

  @Override
  @Async("defaultExecutor")
  @Transactional(readOnly = true)
  public CompletableFuture<EditorDTO> findById(Long id) {
    return CompletableFuture.supplyAsync(
        () -> mapper.toDTO(queryService.verityEditorIdExistReturn(id)));
  }

  @Override
  @Async("defaultExecutor")
  @Transactional(readOnly = true)
  public CompletableFuture<EditorModel> findByIdModel(Long id) {
    return CompletableFuture.supplyAsync(() -> queryService.verityEditorIdExistReturn(id));
  }

  @Override
  @Async("defaultExecutor")
  @Transactional(readOnly = true)
  public CompletableFuture<List<EditorDTO>> findAll(FutDTO futDTO) {
    queryService.verityFutExist(futDTO); // sincrono
    var response =
        repository
            .findByFutId(futDTO.id()) // sincrono
            .stream()
            .map(mapper::toDTO)
            .toList();
    return CompletableFuture.completedFuture(response);
  }

  @Override
  @Async("defaultExecutor")
  public CompletableFuture<Void> delete(Long id) {
    queryService.verityEditorIdExist(id);
    repository.deleteById(id);
    return CompletableFuture.completedFuture(null);
  }
}
