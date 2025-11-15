package br.com.vemprofut.repositories;

import br.com.vemprofut.models.HistoricoPeladeiroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoPeladeiroRepository
    extends JpaRepository<HistoricoPeladeiroModel, Long> {}
