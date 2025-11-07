package br.com.vemprofut.repositories;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.enuns.CartaoCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartoesRepository extends JpaRepository<CartoesModel, Long> {

    List<CartoesModel> findByPeladeiroId(Long peladeiroId);
    List<CartoesModel> findByPartidaId(Long partidaId);
    List<CartoesModel> findByFutId(Long futId);

    @Query("""
           SELECT c.tipo AS tipo, COUNT(c) AS quantidade
           FROM CartoesModel c
           WHERE c.peladeiro.id = :peladeiroId
           GROUP BY c.tipo
           """)
    List<CartaoCountProjection> countByTipoAndPeladeiro(@Param("peladeiroId") Long peladeiroId);

    @Query("""
           SELECT c.tipo AS tipo, COUNT(c) AS quantidade
           FROM CartoesModel c
           WHERE c.fut.id = :futId
           GROUP BY c.tipo
           """)
    List<CartaoCountProjection> countByTipoAndFut(@Param("futId") Long futId);

    /*
    Agora o retorno já vem fortemente tipado: cada item da lista é um 'CartaoCountProjection' com getTipo()
     e getQuantidade().
     */

}
