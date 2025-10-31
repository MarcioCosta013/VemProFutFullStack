package br.com.vemprofut.repository;

import br.com.vemprofut.models.PartidasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidasRepository extends JpaRepository<PartidasModel, Long> {
}
