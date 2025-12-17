package br.com.vemprofut.unit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.mappers.IHistoricoPeladeiroMapper;
import br.com.vemprofut.mappers.IPeladeiroMapper;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.ICartoesService;
import br.com.vemprofut.services.IHistoricoPeladeiroService;
import br.com.vemprofut.services.implementacao.PeladeiroService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PeladeiroServiceTest {

  @InjectMocks private PeladeiroService peladeiroService;

  @Mock private IPeladeiroQueryService queryService;

  @Mock private PeladeiroRepository repository;

  @Mock private IPeladeiroMapper peladeiroMapper;

  @Mock private IHistoricoPeladeiroMapper historicoMapper;

  @Mock private IHistoricoPeladeiroService historicoPeladeiroService;

  @Mock private ICartoesService cartoesService;

  @Test
  @DisplayName("Deve Salvar um novo Peladeiro")
  void create_quandoPeladeiroValido() {
    SavePeladeiroRequestDTO savePeladeiroRequestDTO =
        new SavePeladeiroRequestDTO(
            "Marcio",
            "marcio-costa@gmail.com",
            "ronaldinho",
            "o cara forte",
            "destro",
            "81999999999");
    PeladeiroModel model = new PeladeiroModel();
    model.setId(1L);

    // cria listas simuladas
    List<Long> partidas = List.of(10L, 20L);
    List<Long> futs = List.of(30L);
    List<Long> cartoes = List.of(40L, 50L);

    when(peladeiroMapper.saveRequestToModel(savePeladeiroRequestDTO)).thenReturn(model);
    when(repository.save(model)).thenReturn(model);
    when(peladeiroMapper.modelToSaveResponse(model))
        .thenReturn(
            new SavePeladeiroResponseDTO(
                1L,
                "Marcio",
                "marcio-costa@gmail.com",
                "ronaldinho",
                "o cara forte",
                "destro",
                "81999999999",
                1L,
                "foto.com/url",
                partidas,
                futs,
                cartoes));
    SavePeladeiroResponseDTO response = peladeiroService.create(savePeladeiroRequestDTO);

    assertThat(response.nome()).isEqualTo("Marcio");

    verify(repository, times(2)).save(model);
    verify(peladeiroMapper).saveRequestToModel(savePeladeiroRequestDTO);
    verify(peladeiroMapper).modelToSaveResponse(model);
  }
}
