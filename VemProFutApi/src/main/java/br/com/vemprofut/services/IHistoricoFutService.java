package br.com.vemprofut.services;

import br.com.vemprofut.models.DTOs.HistoricoFutDTO;
import br.com.vemprofut.models.HistoricoFutModel;
import br.com.vemprofut.models.PeladeiroModel;
import java.util.List;

public interface IHistoricoFutService {

  HistoricoFutModel create();

  HistoricoFutDTO findById(Long id);

  HistoricoFutModel findByIdModel(Long id);

  void updateTimeMaisVitorias(Long id, List<PeladeiroModel> time);

  void addPartidasJogadas(Long id, Integer numero);

  void addGolsTotal(Long id, Integer numero);

  void delete(Long id);
}
