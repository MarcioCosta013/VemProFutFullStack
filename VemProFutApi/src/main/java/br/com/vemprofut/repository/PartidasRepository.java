package br.com.vemprofut.repositories;

import br.com.vemprofut.models.PartidasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidasRepository extends JpaRepository<PartidasModel, Long> {
}
