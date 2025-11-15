package br.com.vemprofut.repositories;

import br.com.vemprofut.models.GolsPartidaModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GolsPartidaRepository extends JpaRepository<GolsPartidaModel, Long> {
  @Query("SELECT p FROM GolsPartidaModel p WHERE p.peladeiro.id = :id")
  List<GolsPartidaModel> findByPeladeiro(@Param("id") Long id);

  @Query("SELECT g FROM GolsPartidaModel g WHERE g.partida.id = :id")
  List<GolsPartidaModel> findByPartida(@Param("id") Long id);
}
