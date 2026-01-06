package br.com.vemprofut.unit.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

import br.com.vemprofut.controllers.request.SaveFutRequestDTO;
import br.com.vemprofut.controllers.response.FutDetailsResponse;
import br.com.vemprofut.controllers.response.SaveFutResponseDTO;
import br.com.vemprofut.exceptions.NomeInUseException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.mappers.IFutMapper;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.HistoricoFutModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.*;
import br.com.vemprofut.services.IHistoricoFutService;
import br.com.vemprofut.services.implementacao.FutService;
import br.com.vemprofut.services.query.IFutQueryService;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FutServiceTest {

  @Mock private IFutQueryService queryService;
  @Mock private IFutMapper mapper;
  @Mock private FutRepository repository;
  @Mock private IPeladeiroQueryService peladeiroQueryService;
  @Mock private IHistoricoFutService historicoFutService;

  @InjectMocks private FutService futService;

  FutModel futModel;
  PeladeiroModel peladeiroModel;
  HistoricoFutModel historicoFutModel;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    peladeiroModel = new PeladeiroModel();
    peladeiroModel.setId(1L);
    peladeiroModel.setNome("Marcio");
    peladeiroModel.setEmail("marcio-costa@gmail.com");

    historicoFutModel = new HistoricoFutModel();
    historicoFutModel.setId(1L);

    futModel = new FutModel();
    futModel.setId(1L);
    futModel.setNome("Fut teste");
    futModel.setAdministradorPeladeiro(peladeiroModel);
  }

  // ======================== CRUD basico ==========================
  @Test
  @DisplayName("Deve criar e salvar um novo Fut")
  void create_quandoFutValido_retornaFutSalvo() {
    // Arrange:
    SaveFutRequestDTO saveFutRequestDTO = new SaveFutRequestDTO("Fut teste", 4, 10, 2, 1L);

    SaveFutResponseDTO saveFutResponseDTO =
        new SaveFutResponseDTO(
            1L, "Fut teste", 4, 10, 2, historicoFutModel.getId(), peladeiroModel.getId());

    when(queryService.verifyNomeFutExist(futModel.getNome()))
        .thenReturn(CompletableFuture.completedFuture(null));
    when(peladeiroQueryService.verifyPeladeiroExist(saveFutRequestDTO.administradorPeladeiro()))
        .thenReturn(CompletableFuture.completedFuture(peladeiroModel));
    when(mapper.saveRequestToModel(saveFutRequestDTO)).thenReturn(futModel);
    when(repository.save(futModel)).thenReturn(futModel);
    when(historicoFutService.create()).thenReturn(historicoFutModel);
    when(mapper.toSaveResponse(futModel)).thenReturn(saveFutResponseDTO);

    // Act:
    SaveFutResponseDTO response = futService.create(saveFutRequestDTO).join();

    // Assert:
    assertThat(response.id()).isNotNull().isEqualTo(1L);
    assertThat(response.nome()).isEqualTo("Fut teste");
    assertThat(response.historicoFutId()).isEqualTo(historicoFutModel.getId());
    assertThat(response.administradorPeladeiro()).isEqualTo(peladeiroModel.getId());

    verify(queryService).verifyNomeFutExist(saveFutRequestDTO.nome());
    verify(peladeiroQueryService).verifyPeladeiroExist(saveFutRequestDTO.administradorPeladeiro());
    verify(mapper).saveRequestToModel(saveFutRequestDTO);
    verify(mapper).toSaveResponse(futModel);
    verify(repository, times(2)).save(futModel);
    verify(historicoFutService).create();
  }

  @Test
  @DisplayName("Deve retornar erro ao tentar criar um fut")
  void create_quandoFutInvalido() {
    // Arrange:
    SaveFutRequestDTO saveFutRequestDTO = new SaveFutRequestDTO("Fut teste", 4, 10, 2, 1L);

    doThrow(new NomeInUseException("O nome '" + saveFutRequestDTO.nome() + "' já está cadastrado!"))
        .when(queryService)
        .verifyNomeFutExist(saveFutRequestDTO.nome());

    // Act + Assert:
    assertThatThrownBy(() -> futService.create(saveFutRequestDTO))
        .isInstanceOf(NomeInUseException.class)
        .hasMessage("O nome '" + saveFutRequestDTO.nome() + "' já está cadastrado!");

    verify(queryService).verifyNomeFutExist(saveFutRequestDTO.nome());
    verifyNoInteractions(repository);
  }

  @Test
  @DisplayName("Deve retornar FutDetailsResponse buscando pelo ID")
  void findById_quandoFutExiste_retornaFutDetailsResponse() {

    // Arrange:
    FutDetailsResponse futDetailsResponse =
        new FutDetailsResponse(1L, "Fut teste", 4, 10, 2, 1L, 1L);

    when(queryService.verifyFutExistRetorn(1L))
        .thenReturn(CompletableFuture.completedFuture(futModel));
    when(mapper.modelToDetailsResponse(futModel)).thenReturn(futDetailsResponse);

    // Act:
    FutDetailsResponse response = futService.findById(1L).join();

    // Assert
    assertThat(response.id()).isNotNull().isEqualTo(1L);
    assertThat(response.nome()).isEqualTo("Fut teste");

    verify(queryService).verifyFutExistRetorn(1L);
    verify(mapper).modelToDetailsResponse(futModel);
  }

  @Test
  @DisplayName("Deve retorna NotFoundException ao buscar pelo ID")
  void findById_quandoFutInexistente_retornaNotFoundException() {

    // Arrange:

    when(queryService.verifyFutExistRetorn(99L))
        .thenThrow(new NotFoundException("Não foi encontrado o Fut de id " + 99L));

    // Act + Assert
    assertThatThrownBy(() -> futService.findById(99L))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Não foi encontrado o Fut de id " + 99L);
    verify(queryService).verifyFutExistRetorn(99L);
  }

  @Test
  @DisplayName("Deve buscar Fut pelo Nome")
  void findByNome_quandoFutExiste_retornaFutDTO() {
    // Arrange
    List<Long> editores = List.of(3L, 6L, 7L);
    List<Long> peladeiros = List.of(2L, 8L, 3L, 6L, 7L);
    List<Long> cartoes = List.of(1L, 2L, 3L, 5L);
    FutDTO futDTO = new FutDTO(1L, "Fut Test", 4, 10, 2, 1L, 1L, editores, peladeiros, cartoes);

    when(queryService.verifyNomeFutExistRetorn("Fut Test"))
        .thenReturn(CompletableFuture.completedFuture(futModel));
    when(mapper.toDTO(futModel)).thenReturn(futDTO);

    // Act
    FutDTO response = futService.findByNome("Fut Test").join();

    // Assert
    assertThat(response).isNotNull();
    assertThat(response.id()).isEqualTo(futDTO.id());
    assertThat(response.nome()).isEqualTo("Fut Test");
    assertThat(response.editores().size()).isEqualTo(3);
    assertThat(response.peladeiros().size()).isEqualTo(5);
    assertThat(response.cartoes().size()).isEqualTo(4);

    verify(queryService).verifyNomeFutExistRetorn("Fut Test");
    verify(mapper).toDTO(futModel);
  }

  @Test
  @DisplayName("Deve buscar um nome de Fut que nao existe e retornar ")
  void findByNome_quandoFutInexistente_retornaEntityNotFoundException() {}
}
