package br.com.vemprofut.repositories;

import br.com.vemprofut.models.GolsPartidaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GolsPartidaRepository extends JpaRepository<GolsPartidaModel, Long> {

    List<GolsPartidaModel> findByPeladeiro(Long id);

    List<GolsPartidaModel> findByPartida(Long id);
}
