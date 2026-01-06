package br.com.vemprofut.unit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.request.UpdatePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.CartoesResumoResponseDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.UpdatePeladeiroResponseDTO;
import br.com.vemprofut.exceptions.EmailInUseException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.mappers.IHistoricoPeladeiroMapper;
import br.com.vemprofut.mappers.IPeladeiroMapper;
import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.models.enuns.PeDominante;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.ICartoesService;
import br.com.vemprofut.services.IHistoricoPeladeiroService;
import br.com.vemprofut.services.implementacao.PeladeiroService;
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
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class PeladeiroServiceTest {

  @Mock private IPeladeiroQueryService queryService;
  @Mock private PeladeiroRepository repository;
  @Mock private IPeladeiroMapper peladeiroMapper;
  @Mock private ICartoesService cartoesService;
  @Mock private IHistoricoPeladeiroService historicoPeladeiroService;
  @Mock private IHistoricoPeladeiroMapper historicoPeladeiroMapper;

  @InjectMocks private PeladeiroService peladeiroService;

  PeladeiroModel peladeiroModel;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    peladeiroModel = new PeladeiroModel();
    peladeiroModel.setId(1L);
    peladeiroModel.setNome("Marcio");
    peladeiroModel.setEmail("marcio-costa@gmail.com");
  }

  @Test
  @DisplayName("Deve Salvar um novo Peladeiro")
  void create_quandoPeladeiroValido() {
    // Arrange
    SavePeladeiroRequestDTO savePeladeiroRequestDTO =
        new SavePeladeiroRequestDTO(
            "Marcio",
            "marcio-costa@gmail.com",
            "ronaldinho",
            "o cara forte",
            PeDominante.DESTRO,
            "81999999999");

    HistoricoPeladeiroDTO historicoPeladeiroDTO = new HistoricoPeladeiroDTO(1L, 10, 9.5, 5, 3);

    HistoricoPeladeiroModel historicoPeladeiroModel = new HistoricoPeladeiroModel();

    // cria listas simuladas
    List<Long> partidas = List.of(10L, 20L);
    List<Long> futs = List.of(30L);
    List<Long> cartoes = List.of(40L, 50L);

    when(queryService.verifyEmail(savePeladeiroRequestDTO.email()))
        .thenReturn(CompletableFuture.completedFuture(null));
    when(historicoPeladeiroService.create())
        .thenReturn(CompletableFuture.completedFuture(historicoPeladeiroDTO));
    when(historicoPeladeiroMapper.toModel(historicoPeladeiroDTO))
        .thenReturn(historicoPeladeiroModel);
    when(peladeiroMapper.saveRequestToModel(savePeladeiroRequestDTO)).thenReturn(peladeiroModel);
    when(repository.save(peladeiroModel)).thenReturn(peladeiroModel);
    when(peladeiroMapper.modelToSaveResponse(peladeiroModel))
        .thenReturn(
            new SavePeladeiroResponseDTO(
                1L,
                "Marcio",
                "marcio-costa@gmail.com",
                "ronaldinho",
                "o cara forte",
                PeDominante.DESTRO,
                "81999999999",
                1L,
                "foto.com/url",
                partidas,
                futs,
                cartoes));
    // Act
    SavePeladeiroResponseDTO response = peladeiroService.create(savePeladeiroRequestDTO).join();

    // Assert
    assertThat(response.nome()).isEqualTo("Marcio");

    verify(queryService).verifyEmail(savePeladeiroRequestDTO.email());
    verify(historicoPeladeiroService).create();
    verify(historicoPeladeiroMapper).toModel(historicoPeladeiroDTO);
    verify(repository, times(2)).save(peladeiroModel);
    verify(peladeiroMapper).saveRequestToModel(savePeladeiroRequestDTO);
    verify(peladeiroMapper).modelToSaveResponse(peladeiroModel);
  }

  @Test
  @DisplayName("Deve retornar um EmailInUseException por email ja existente")
  void create_quandoEmailJaCadastrado_retornaEmailInUseException() {
    // Arrange
    SavePeladeiroRequestDTO savePeladeiroRequestDTO =
        new SavePeladeiroRequestDTO(
            "Marcio",
            "marcio-costa@gmail.com",
            "ronaldinho",
            "o cara forte",
            PeDominante.DESTRO,
            "81999999999");

    doThrow(
            new EmailInUseException(
                "O e-mail " + savePeladeiroRequestDTO.email() + " já está em uso"))
        .when(queryService)
        .verifyEmail(savePeladeiroRequestDTO.email());

    // Act + Assert
    assertThatThrownBy(() -> peladeiroService.create(savePeladeiroRequestDTO))
        .isInstanceOf(EmailInUseException.class)
        .hasMessage("O e-mail " + savePeladeiroRequestDTO.email() + " já está em uso");

    verify(queryService).verifyEmail(savePeladeiroRequestDTO.email());
    verifyNoInteractions(repository);
  }

  @Test
  @DisplayName("Deve fazer uma Alteracao em um Peladeiro ja cadastrado")
  void update_quandoPeladeiroExiste_retornaPeladeiroAlterado() {
    // Arrange
    UpdatePeladeiroRequestDTO requestDTO =
        new UpdatePeladeiroRequestDTO(
            "maria",
            "maria@gmail.com",
            "mari",
            "mulher que joga",
            PeDominante.CANHOTO,
            "81999993332");

    when(queryService.verifyPeladeiroExist(1L))
        .thenReturn(CompletableFuture.completedFuture(peladeiroModel));
    when(repository.save(peladeiroModel)).thenReturn(peladeiroModel);
    when(peladeiroMapper.modelToUpdateResponse(peladeiroModel))
        .thenReturn(
            new UpdatePeladeiroResponseDTO(
                "maria",
                "maria@gmail.com",
                "mari",
                "mulher que joga",
                "81999993332",
                PeDominante.DESTRO,
                "foto.com/url"));

    // Act
    UpdatePeladeiroResponseDTO responseDTO = peladeiroService.update(1L, requestDTO).join();

    // Assert
    assertThat(responseDTO).isNotNull();
    assertThat(responseDTO.nome()).isEqualTo("maria");
    assertThat(responseDTO.email()).isEqualTo("maria@gmail.com");

    verify(queryService).verifyPeladeiroExist(1L);
    verify(repository).save(peladeiroModel);
  }

  @Test
  @DisplayName("Deve retornar NotFoundException por nao encontrar o Peladeiro pelo ID")
  void update_quandoPeladeiroInexistente_retornaNotFoundException() {
    // Arrange
    UpdatePeladeiroRequestDTO requestDTO =
        new UpdatePeladeiroRequestDTO(
            "maria",
            "maria@gmail.com",
            "mari",
            "mulher que joga",
            PeDominante.CANHOTO,
            "81999993332");

    when(queryService.verifyPeladeiroExist(99L))
        .thenThrow(new NotFoundException("Não foi encontrado o Peladeiro de id" + 99L));

    // Act + Assert
    assertThatThrownBy(() -> peladeiroService.update(99L, requestDTO))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Não foi encontrado o Peladeiro de id" + 99L);
    verify(queryService).verifyPeladeiroExist(99L);
    verifyNoInteractions(repository);
  }

  @Test
  @DisplayName("Deve buscar um peladeiro pelo ID")
  void findById_quandoPeladeiroExiste_retornaPeladeiroDetailResponse() {
    // Arrange
    CartoesResumoResponseDTO cartoesResumoResponseDTO = new CartoesResumoResponseDTO(2, 3, 2);
    when(queryService.verifyPeladeiroExist(1L))
        .thenReturn(CompletableFuture.completedFuture(peladeiroModel));
    when(cartoesService.contarCartoesPeladeiro(1L))
        .thenReturn(CompletableFuture.completedFuture(cartoesResumoResponseDTO));

    // Act
    PeladeiroDetailResponse peladeiroDetailResponse = peladeiroService.findById(1L).join();

    // Assert
    assertThat(peladeiroDetailResponse).isNotNull();
    assertThat(peladeiroDetailResponse.id()).isEqualTo(1L);
    assertThat(peladeiroDetailResponse.cartoes().getAzul()).isEqualTo(2);
    assertThat(peladeiroDetailResponse.cartoes().getAmarelo()).isEqualTo(3);
    assertThat(peladeiroDetailResponse.cartoes().getVermelho()).isEqualTo(2);
    assertThat(peladeiroDetailResponse.nome()).isEqualTo("Marcio");
    assertThat(peladeiroDetailResponse.email()).isEqualTo("marcio-costa@gmail.com");

    verify(queryService).verifyPeladeiroExist(1L);
    verify(cartoesService).contarCartoesPeladeiro(1L);
  }

  @Test
  @DisplayName("Deve buscar um peladeiro pelo ID")
  void findById_quandoPeladeiroInexistente_retornaNotFoundException() {
    // Arrange
    when(queryService.verifyPeladeiroExist(99L))
        .thenThrow(new NotFoundException("Não foi encontrado o Peladeiro de id " + 99L));

    // Act + Assert
    assertThatThrownBy(() -> peladeiroService.findById(99L))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Não foi encontrado o Peladeiro de id " + 99L);

    verify(queryService).verifyPeladeiroExist(99L);
    verifyNoInteractions(
        repository); // é usando em verifyPeladeiroExist, mas como ele está mockado nao detecta...
  }

  @Test
  @DisplayName("Deve retornar PeladeiroModel quando ID existe")
  void findByIdModel_quandoPeladeiroExiste_retornaPeladeiroModel() {
    // Arrenge
    when(queryService.verifyPeladeiroExist(1L))
        .thenReturn(CompletableFuture.completedFuture(peladeiroModel));

    // Act
    PeladeiroModel response = peladeiroService.findByIdModel(1L).join();

    // Assert
    assertThat(response.getId()).isEqualTo(1L);
    assertThat(response.getNome()).isEqualTo("Marcio");
    assertThat(response.getEmail()).isEqualTo("marcio-costa@gmail.com");

    verify(queryService).verifyPeladeiroExist(1L);
  }

  @Test
  @DisplayName("Deve retornar erro NotFoundException quando ID nao existe")
  void findByIdModel_quandoPeladeiroInexistente_retornaNotFoundException() {
    // Arrenge
    when(queryService.verifyPeladeiroExist(99L))
        .thenThrow(new NotFoundException("Não foi encontrado o Peladeiro de id " + 99L));

    // Act + Assert
    assertThatThrownBy(() -> peladeiroService.findByIdModel(99L))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Não foi encontrado o Peladeiro de id " + 99L);

    verify(queryService).verifyPeladeiroExist(99L);
  }

  @Test
  @DisplayName("Deve apagar o Peladeiro indicado pelo numero do ID")
  void delete_quandoPeladeiroExiste() {
    // Arrenge
    when(queryService.verifyPeladeiroExist(1L))
        .thenReturn(CompletableFuture.completedFuture(peladeiroModel));
    doNothing().when(repository).deleteById(1L);

    // Act
    peladeiroService.delete(1L).join();

    // Assert
    verify(queryService).verifyPeladeiroExist(1L);
    verify(repository).deleteById(1L);
  }

  @Test
  @DisplayName("Deve apagar o Peladeiro indicado pelo numero do ID")
  void delete_quandoPeladeiroInexistente() {
    // Arrenge
    when(queryService.verifyPeladeiroExist(99L))
        .thenThrow(new NotFoundException("Não foi encontrado o Peladeiro de id " + 99L));

    // Act + Assert

    assertThatThrownBy(() -> peladeiroService.delete(99L))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Não foi encontrado o Peladeiro de id " + 99L);

    verify(queryService).verifyPeladeiroExist(99L);
  }

  @Test
  @DisplayName("Deve atualizar a foto do Peladeiro")
  void atualizarFoto_quandoPeladeiroExiste() {
    MultipartFile file = mock(MultipartFile.class);

    when(queryService.verifyPeladeiroSaveFile(1L, file))
        .thenReturn(CompletableFuture.completedFuture(null));
    peladeiroService.atualizarFoto(1L, file).join();

    verify(queryService).verifyPeladeiroSaveFile(1L, file);
  }

  @Test
  @DisplayName("Deve lançar NotFoundException ao tentar atualizar foto de Peladeiro inexistente")
  void atualizarFoto_quandoPeladeiroInexistente_retornaNotFoundException() {
    MultipartFile file = mock(MultipartFile.class);

    // Arrange: simula que o queryService lança exceção
    doThrow(new NotFoundException("Não foi encontrado o Peladeiro de id 99"))
        .when(queryService)
        .verifyPeladeiroSaveFile(99L, file);

    // Act + Assert
    assertThatThrownBy(() -> peladeiroService.atualizarFoto(99L, file).join())
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Não foi encontrado o Peladeiro de id 99");

    verify(queryService).verifyPeladeiroSaveFile(99L, file);
  }
}
