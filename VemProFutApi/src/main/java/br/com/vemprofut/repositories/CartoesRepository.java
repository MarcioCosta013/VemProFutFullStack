package br.com.vemprofut.repositories;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartaoCountProjection;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartoesRepository extends JpaRepository<CartoesModel, Long> {

    List<CartoesModel> findByPeladeiro(PeladeiroModel peladeiro);
    List<CartoesModel> findByPartida(PartidasModel partida);
    List<CartoesModel> findByFut(FutModel fut);

    @Query("""
           SELECT c.tipoCartao AS tipo, COUNT(c) AS quantidade
           FROM CartoesModel c
           WHERE c.peladeiroIdCartoes.id = :peladeiroId
           GROUP BY c.tipoCartao
           """)
    List<CartaoCountProjection> countByTipoAndPeladeiro(@Param("peladeiroId") Long peladeiroId);

    @Query("""
           SELECT c.tipoCartao AS tipo, COUNT(c) AS quantidade
           FROM CartoesModel c
           WHERE c.futId.id = :futId
           GROUP BY c.tipoCartao
           """)
    List<CartaoCountProjection> countByTipoAndFut(@Param("futId") Long futId);

    /*
    Agora o retorno já vem fortemente tipado: cada item da lista é um 'CartaoCountProjection' com getTipo()
     e getQuantidade().
     */

}
