package br.com.vemprofut.repositories;

import br.com.vemprofut.models.FutModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FutRepository extends JpaRepository<FutModel, Long> {

  boolean existsByNomeStartingWith(String nomeFut);

  Optional<FutModel> findByNome(String nome);

  @Query(
      """
          SELECT f FROM FutModel f JOIN FETCH f.peladeiros WHERE f.id = :id
          """)
  Optional<FutModel> buscarFutComListPeladeiros(@Param("id") Long id);
  /*
  Você diz ao Hibernate: "Busque o FutModel e já aproveita para carregar os peladeiros"

  Na prática é:

  SELECT * FROM fut f JOIN peladeiros_fut pf ON pf.fut_id = f.id JOIN peladeiro p ON p.id = pf.peladeiro_id
  WHERE f.id = ?
   */
}
