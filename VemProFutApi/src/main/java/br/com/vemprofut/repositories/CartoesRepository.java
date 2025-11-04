package br.com.vemprofut.repositories;

import br.com.vemprofut.models.CartoesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartoesRepository extends JpaRepository<CartoesModel, Long> {

    List<CartoesModel> findByPeladeiroId(Long peladeiroId);
    List<CartoesModel> findByPartidaId(Long partidaId);
    List<CartoesModel> findByFutId(Long futId);

}
