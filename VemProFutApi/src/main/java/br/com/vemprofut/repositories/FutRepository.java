package br.com.vemprofut.repositories;

import br.com.vemprofut.models.FutModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FutRepository extends JpaRepository<FutModel, Long> {
  
  boolean existsByNomeStartingWith(String nomeFut);

  Optional<FutModel> findByNome(String nome);
}
