package br.com.vemprofut.repository;

import br.com.vemprofut.models.EditorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorRepository extends JpaRepository<EditorModel, Long> {
}
