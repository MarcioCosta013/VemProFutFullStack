package br.com.vemprofut.repositories;

import br.com.vemprofut.models.FutModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FutRepository extends JpaRepository<FutModel, Long> {

    Boolean existsByNomeStartingWith(String nomeFut);

    Optional<FutModel> findByName(String nome);
}
