package br.com.vemprofut.repositories;

import br.com.vemprofut.models.BanimentoModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BanimentoRepository extends JpaRepository<BanimentoModel, Long> {

  @Query(
      """
            SELECT b FROM BanimentoModel b WHERE b.fut = :idFut AND b.peladeiro = :idPeladeiro
            """)
  Optional<BanimentoModel> buscarBanimentoFutPeladeiro(
      @Param("idFut") Long idFut, @Param("idPeladeiro") Long idPeladeiro);

  @Query("""
            SELECT b FROM BanimentoModel b WHERE b.fut = :idFut
            """)
  List<BanimentoModel> buscarListBanidos(@Param("idFut") Long idFut);
}
