package br.com.vemprofut.repositories;

import br.com.vemprofut.models.FutModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FutRepository extends JpaRepository<FutModel, Long> {
}
