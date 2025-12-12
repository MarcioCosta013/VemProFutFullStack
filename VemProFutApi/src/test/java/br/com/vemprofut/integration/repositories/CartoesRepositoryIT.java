package br.com.vemprofut.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartaoCountProjection;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.models.enuns.TipoCartao;
import br.com.vemprofut.repositories.CartoesRepository;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.repositories.PartidasRepository;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.implementacao.UploadLocalService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CartoesRepositoryIT {

  @MockitoBean private OAuth2AuthorizedClientService authorizedClientService;

  // Opcional: Mocka o próprio Handler para evitar que ele tente rodar lógica
  @MockitoBean private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @MockitoBean private ClientRegistrationRepository clientRegistrationRepository;

  @MockitoBean private UploadLocalService uploadLocalService;

  @MockitoBean private JwtDecoder jwtDecoder;

  @Autowired
  CartoesRepository cartoesRepository;

  @Autowired
  PartidasRepository partidasRepository;

  @Autowired
  FutRepository futRepository;

  @Autowired
  PeladeiroRepository peladeiroRepository;

  @Test
  @DisplayName("Deve criar um novo cartao ligado ao peladeiro e a partida")
  void save_quandoCartaoValido_retornaIdGerado() {

    PartidasModel partidasModel = partidasRepository.saveAndFlush(new PartidasModel());

    FutModel futModel = futRepository.saveAndFlush(new FutModel());

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva",
                "joao@test.com",
                "Apelido",
                "descricao qualquer",
                "81999999999",
                "Destro"));

    CartoesModel cartoesModel =
        cartoesRepository.saveAndFlush(
            new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AMARELO));

    assertNotNull(cartoesModel.getId());
    assertTrue(cartoesModel.getId() > 0);
  }

  @Test
  @DisplayName("Deve buscar todos cartoes e retorna em uma lista")
  void findAll_quandoCartoesExistem_retornaListaCartao() {
    PartidasModel partidasModel = partidasRepository.saveAndFlush(new PartidasModel());
    PartidasModel partidasModel2 = partidasRepository.saveAndFlush(new PartidasModel());

    FutModel futModel = futRepository.saveAndFlush(new FutModel());
    FutModel futModel2 = futRepository.saveAndFlush(new FutModel());

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva",
                "joao@test.com",
                "Apelido",
                "descricao qualquer",
                "81999999999",
                "Destro"));

    PeladeiroModel peladeiroModel2 =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva2",
                "joao2@test.com",
                "Apelido",
                "descricao qualquer2",
                "81999999999",
                "Destro"));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel2, peladeiroModel2, futModel2, TipoCartao.AMARELO));

    List<CartoesModel> todos = cartoesRepository.findAll();

    assertTrue(todos.size() >= 2);
  }

  @Test
  @DisplayName("Deve buscar um cartao pelo IDe retornar um Optional")
  void findById_quandoCartaoExiste_retornaCartao() {
    PartidasModel partidasModel = partidasRepository.saveAndFlush(new PartidasModel());

    FutModel futModel = futRepository.saveAndFlush(new FutModel());

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva",
                "joao@test.com",
                "Apelido",
                "descricao qualquer",
                "81999999999",
                "Destro"));

    CartoesModel cartoesModel =
        cartoesRepository.saveAndFlush(
            new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AMARELO));

    Long id = cartoesModel.getId();

    assertTrue(cartoesRepository.findById(id).isPresent());
  }

  @Test
  @DisplayName("Deve buscar um cartao pelo ID e retornar um Optional vazio")
  void findById_quandoCartaoExiste_retornaOptinalVazio() {
    PartidasModel partidasModel = partidasRepository.saveAndFlush(new PartidasModel());

    FutModel futModel = futRepository.saveAndFlush(new FutModel());

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva",
                "joao@test.com",
                "Apelido",
                "descricao qualquer",
                "81999999999",
                "Destro"));

    CartoesModel cartoesModel =
        cartoesRepository.saveAndFlush(
            new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AMARELO));

    Long id = 99999L;

    assertTrue(cartoesRepository.findById(id).isEmpty());
  }

  @Test
  @DisplayName("Deve buscar todos os cartoes de um Peladeiro especifico")
  void findByPeladeiro_quandoCartoesDoPeladeiroExistem_retornaListCartoes() {
    PartidasModel partidasModel = partidasRepository.saveAndFlush(new PartidasModel());

    FutModel futModel = futRepository.saveAndFlush(new FutModel());

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva",
                "joao@test.com",
                "Apelido",
                "descricao qualquer",
                "81999999999",
                "Destro"));

    PeladeiroModel peladeiroModel2 =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Paulo Silva",
                "paulo@test.com",
                "Apelido",
                "descricao qualquer2",
                "81999999999",
                "Destro"));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AZUL));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel2, futModel, TipoCartao.AMARELO));

    List<CartoesModel> todosCartoesPeladeiro = cartoesRepository.findByPeladeiro(peladeiroModel);

    assertEquals(2, todosCartoesPeladeiro.size());
  }

  @Test
  @DisplayName("Deve buscar todos os cartoes de uma Partida especifico")
  void findByPartida_quandoCartoesExistem_retornaListCartoes() {
    PartidasModel partidasModel = partidasRepository.saveAndFlush(new PartidasModel());
    PartidasModel partidasModel2 = partidasRepository.saveAndFlush(new PartidasModel());

    FutModel futModel = futRepository.saveAndFlush(new FutModel());

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva",
                "joao@test.com",
                "Apelido",
                "descricao qualquer",
                "81999999999",
                "Destro"));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AZUL));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel2, peladeiroModel, futModel, TipoCartao.AMARELO));

    List<CartoesModel> todosCartoesPeladeiro = cartoesRepository.findByPartida(partidasModel);

    assertEquals(2, todosCartoesPeladeiro.size());
  }

  @Test
  @DisplayName("Deve buscar todos os cartoes de um Peladeiro especifico")
  void findByFut_quandoCartoesExistem_retornaListCartoes() {
    PartidasModel partidasModel = partidasRepository.saveAndFlush(new PartidasModel());

    FutModel futModel = futRepository.saveAndFlush(new FutModel());
    FutModel futModel2 = futRepository.saveAndFlush(new FutModel());

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva",
                "joao@test.com",
                "Apelido",
                "descricao qualquer",
                "81999999999",
                "Destro"));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AZUL));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel2, TipoCartao.AMARELO));

    List<CartoesModel> todosCartoesPeladeiro = cartoesRepository.findByFut(futModel);

    assertEquals(2, todosCartoesPeladeiro.size());
  }

  @Test
  @DisplayName("Deve retornar a quantidade de total de cada cartao de um peladeiro especifico")
  void countByTipoAndPeladeiro_quandoCartoesExistem_retornaListCartoesPeladeiroContado() {

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva",
                "joao@test.com",
                "Apelido",
                "descricao qualquer",
                "81999999999",
                "Destro"));

    PeladeiroModel peladeiroModel2 =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Paulo Silva",
                "paulo@test.com",
                "Apelido",
                "descricao qualquer2",
                "81999999999",
                "Destro"));

    FutModel futModel = futRepository.saveAndFlush(new FutModel());
    FutModel futModel2 = futRepository.saveAndFlush(new FutModel());

    PartidasModel partidasModel = partidasRepository.saveAndFlush(new PartidasModel());
    PartidasModel partidasModel2 = partidasRepository.saveAndFlush(new PartidasModel());

    // dados peladeiro 1
    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AZUL));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel2, peladeiroModel, futModel2, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel2, peladeiroModel, futModel2, TipoCartao.VERMELHO));

    // Dados peladeiro 2
    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel2, futModel, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel2, futModel, TipoCartao.AZUL));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel2, peladeiroModel2, futModel2, TipoCartao.AMARELO));

    List<CartaoCountProjection> contagem =
        cartoesRepository.countByTipoAndPeladeiro(peladeiroModel.getId());

    // Transformar em mapa para facilitar
    Map<TipoCartao, Long> resultado =
        Arrays.stream(TipoCartao.values())
            .collect(
                Collectors.toMap(
                    tipo -> tipo,
                    tipo ->
                        contagem.stream()
                            .filter(c -> c.getTipo() == tipo)
                            .map(CartaoCountProjection::getQuantidade)
                            .findFirst()
                            .orElse(0L)));

    assertEquals(2, resultado.get(TipoCartao.AMARELO));
    assertEquals(1, resultado.get(TipoCartao.AZUL));
    assertEquals(1, resultado.get(TipoCartao.VERMELHO));
  }

  @Test
  @DisplayName("Deve retornar a quantidade de total de cada cartao de um Fut especifico")
  void countByTipoAndFut__quandoCartoesExistem_retornaListCartoesFutContado() {
    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "João da Silva",
                "joao@test.com",
                "Apelido",
                "descricao qualquer",
                "81999999999",
                "Destro"));

    PeladeiroModel peladeiroModel2 =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Paulo Silva",
                "paulo@test.com",
                "Apelido",
                "descricao qualquer2",
                "81999999999",
                "Destro"));

    FutModel futModel = futRepository.saveAndFlush(new FutModel());
    FutModel futModel2 = futRepository.saveAndFlush(new FutModel());

    PartidasModel partidasModel = partidasRepository.saveAndFlush(new PartidasModel());
    PartidasModel partidasModel2 = partidasRepository.saveAndFlush(new PartidasModel());

    // dados Fut 1
    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel2, futModel, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel, futModel, TipoCartao.AZUL));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel, peladeiroModel2, futModel, TipoCartao.AZUL));

    // Dados fut 2

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel2, peladeiroModel2, futModel2, TipoCartao.AMARELO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel2, peladeiroModel, futModel2, TipoCartao.VERMELHO));

    cartoesRepository.saveAndFlush(
        new CartoesModel(partidasModel2, peladeiroModel, futModel2, TipoCartao.AMARELO));

    List<CartaoCountProjection> contagem = cartoesRepository.countByTipoAndFut(futModel.getId());

    // Transformar em mapa para facilitar
    Map<TipoCartao, Long> resultado =
        Arrays.stream(TipoCartao.values())
            .collect(
                Collectors.toMap(
                    tipo -> tipo,
                    tipo ->
                        contagem.stream()
                            .filter(c -> c.getTipo() == tipo)
                            .map(CartaoCountProjection::getQuantidade)
                            .findFirst()
                            .orElse(0L)));
    assertEquals(2, resultado.get(TipoCartao.AMARELO));
    assertEquals(2, resultado.get(TipoCartao.AZUL));
    assertEquals(0, resultado.get(TipoCartao.VERMELHO));
  }
}
