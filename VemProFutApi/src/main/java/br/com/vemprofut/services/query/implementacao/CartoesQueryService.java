package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.repositories.CartoesRepository;
import br.com.vemprofut.services.query.ICartoesQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartoesQueryService implements ICartoesQueryService {

  @Autowired private CartoesRepository repository;

  // Verifica se o peladeiro, partida e fut existem
  public void verifyEntitiesExist(CartoesDTO dto) {
    if (!repository.existsById(dto.peladeiro()))
      throw new IllegalArgumentException("Peladeiro não encontrado");

    if (!repository.existsById(dto.partida()))
      throw new IllegalArgumentException("Partida não encontrada");

    if (!repository.existsById(dto.fut())) throw new IllegalArgumentException("Fut não encontrado");
  }

  public CartoesModel verityCartoesExist(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Não foi encontrado o Cartoes de id " + id));
  }
}
