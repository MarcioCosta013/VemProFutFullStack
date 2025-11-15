package br.com.vemprofut.repositories;

import br.com.vemprofut.models.EditorModel;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorRepository extends JpaRepository<EditorModel, Long> {

  Boolean findByPeladeiroAndFut(PeladeiroModel peladeiroModel, FutModel futModel);

  List<EditorModel> findByFutId(Long id);
}
