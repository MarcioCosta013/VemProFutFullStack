package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import java.util.concurrent.CompletableFuture;

public interface IHistoricoPeladeiroService {

  CompletableFuture<HistoricoPeladeiroDTO> create();

  CompletableFuture<HistoricoPeladeiroDTO> findById(Long id);

  CompletableFuture<HistoricoPeladeiroModel> findByIdModel(Long id);

  CompletableFuture<HistoricoPeladeiroDTO> update(Long id, HistoricoPeladeiroDTO dto);

  CompletableFuture<Void> delete(Long id);
}
