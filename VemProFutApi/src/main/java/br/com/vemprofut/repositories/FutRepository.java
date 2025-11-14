package br.com.vemprofut.repositories;

import br.com.vemprofut.models.FutModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FutRepository extends JpaRepository<FutModel, Long> {

    @Query("""
            SELECT * FROM fut WHERE nome_fut = :nomeFut
            """)
    Boolean existsByNomeStartingWith(@Param("nomeFut") String nomeFut);

    Optional<FutModel> findByNome(String nome);
}
