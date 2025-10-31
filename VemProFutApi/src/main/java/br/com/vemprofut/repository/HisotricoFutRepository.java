package br.com.vemprofut.repository;

import br.com.vemprofut.models.HistoricoFutModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HisotricoFutRepository extends JpaRepository<HistoricoFutModel,Long> {
}
