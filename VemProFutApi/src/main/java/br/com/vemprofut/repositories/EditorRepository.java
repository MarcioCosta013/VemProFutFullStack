package br.com.vemprofut.repositories;

import br.com.vemprofut.models.EditorModel;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EditorRepository extends JpaRepository<EditorModel, Long> {

  @Query("""
          SELECT e FROM EditorModel e WHERE e.peladeiro = :peladeiro AND e.fut= :fut
         """)
  EditorModel findByPeladeiroAndFut(
      @Param("peladeiro") PeladeiroModel peladeiroModel,
      @Param("fut") FutModel futModel
  );

  List<EditorModel> findByFutId(Long id);
}
