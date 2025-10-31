package br.com.vemprofut.repository;

import br.com.vemprofut.models.CartoesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartoesRepository extends JpaRepository<CartoesModel, Long> {
}
