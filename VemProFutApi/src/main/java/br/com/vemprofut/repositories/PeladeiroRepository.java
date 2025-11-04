package br.com.vemprofut.repositories;

import br.com.vemprofut.models.PeladeiroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeladeiroRepository extends JpaRepository<PeladeiroModel, Long> {

    Boolean existsByEmail(final String email);

}
