package br.com.vemprofut.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.models.enuns.PeDominante;
import br.com.vemprofut.repositories.GolsPartidaRepository;
import br.com.vemprofut.repositories.PartidasRepository;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.implementacao.UploadLocalService;
import java.util.List;
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
public class GolsPartidaRepositoryIT {

  @MockitoBean private OAuth2AuthorizedClientService authorizedClientService;

  @MockitoBean private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @MockitoBean private ClientRegistrationRepository clientRegistrationRepository;

  @MockitoBean private UploadLocalService uploadLocalService;

  @MockitoBean private JwtDecoder jwtDecoder;

  @Autowired PeladeiroRepository peladeiroRepository;

  @Autowired PartidasRepository partidasRepository;

  @Autowired GolsPartidaRepository golsPartidaRepository;

  @Test
  @DisplayName("Deve salvo um novo GolPartida")
  void save_quandoGolPartidaValido_retornaGolPartidaSalvo() {
    PeladeiroModel peladeiroSalvo =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.DESTRO));
    PartidasModel partidasSalvo = partidasRepository.saveAndFlush(new PartidasModel());

    GolsPartidaModel golsPartidaSalvo =
        golsPartidaRepository.save(new GolsPartidaModel(peladeiroSalvo, partidasSalvo));

    assertNotNull(golsPartidaSalvo.getId());
    assertTrue(golsPartidaSalvo.getId() > 0);
  }

  @Test
  @DisplayName("Deve buscar um GolPartida pelo ID")
  void findById_quandoGolPartidaExiste_retornaGolPartidaModel() {
    PeladeiroModel peladeiroSalvo =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.DESTRO));
    PartidasModel partidasSalvo = partidasRepository.saveAndFlush(new PartidasModel());
    GolsPartidaModel golsPartidaSalvo =
        golsPartidaRepository.save(new GolsPartidaModel(peladeiroSalvo, partidasSalvo));
    Long id = golsPartidaSalvo.getId();

    assertTrue(golsPartidaRepository.findById(id).isPresent());
  }

  @Test
  @DisplayName("Deve buscar um GolPartida pelo ID e Retorna null")
  void findById_quandoGolPartidaInexistente_retornaNull() {
    PeladeiroModel peladeiroSalvo =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.DESTRO));
    PartidasModel partidasSalvo = partidasRepository.saveAndFlush(new PartidasModel());
    golsPartidaRepository.save(new GolsPartidaModel(peladeiroSalvo, partidasSalvo));

    assertTrue(golsPartidaRepository.findById(9999L).isEmpty());
  }

  @Test
  @DisplayName("Deve verificar pelo ID se um GolPartida existe e retorna true")
  void existsById_quandoGolPartidaExiste_retornaTrue() {
    PeladeiroModel peladeiroSalvo =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.DESTRO));
    PartidasModel partidasSalvo = partidasRepository.saveAndFlush(new PartidasModel());
    GolsPartidaModel golsPartidaSalvo =
        golsPartidaRepository.save(new GolsPartidaModel(peladeiroSalvo, partidasSalvo));
    Long id = golsPartidaSalvo.getId();

    assertTrue(golsPartidaRepository.existsById(id));
  }

  @Test
  @DisplayName("Deve verificar pelo ID se um GolPartida existe e retorna true")
  void existsById_quandoGolPartidaExiste_retornaFalse() {
    PeladeiroModel peladeiroSalvo =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.DESTRO));
    PartidasModel partidasSalvo = partidasRepository.saveAndFlush(new PartidasModel());
    golsPartidaRepository.save(new GolsPartidaModel(peladeiroSalvo, partidasSalvo));

    assertFalse(golsPartidaRepository.existsById(9999L));
  }

  @Test
  @DisplayName("Deve retornar todos os gols")
  void findAll_quandoExitemGolPartidas_retornaListGolPartidaModel() {
    for (int i = 0; i < 4; i++) {
      PeladeiroModel peladeiroSalvo =
          peladeiroRepository.saveAndFlush(
              new PeladeiroModel(
                  "Marcio Teste" + i,
                  "teste" + i + "@test.com",
                  "Ronaldo",
                  "o cara nota 10",
                  "81992235678",
                  PeDominante.DESTRO));
      PartidasModel partidasSalvo = partidasRepository.saveAndFlush(new PartidasModel());
      golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroSalvo, partidasSalvo));
    }

    List<GolsPartidaModel> todos = golsPartidaRepository.findAll();

    assertTrue(todos.size() > 2);
  }

  @Test
  @DisplayName("Deve buscar todos os gols de um peladeiro")
  void findByPeladeiro_quandoGolPartidaExistirem_retornaListaGolPartidas() {
    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.DESTRO));
    PeladeiroModel peladeiroModel2 =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel("A", "a@test.com", "A", "333", "81555555555", PeDominante.DESTRO));

    PartidasModel partidasSalvo = partidasRepository.saveAndFlush(new PartidasModel());
    PartidasModel partidasSalvo2 = partidasRepository.saveAndFlush(new PartidasModel());

    // Gols peladeiro1
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo2));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo2));

    // Gols peladeiro2
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel2, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel2, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel2, partidasSalvo2));
    Long id = peladeiroModel.getId();

    List<GolsPartidaModel> todos = golsPartidaRepository.findByPeladeiro(id);

    assertTrue(todos.size() > 4);
  }

  @Test
  @DisplayName("Deve buscar todos os gols de uma partida em especifico")
  void findByPartida_quandoGolPartidaExistirem_retornaListaGolPartidas() {
    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.DESTRO));
    PeladeiroModel peladeiroModel2 =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel("A", "a@test.com", "A", "333", "81555555555", PeDominante.DESTRO));

    PartidasModel partidasSalvo = partidasRepository.saveAndFlush(new PartidasModel());
    PartidasModel partidasSalvo2 = partidasRepository.saveAndFlush(new PartidasModel());

    // Gols peladeiro1
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo2));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel, partidasSalvo2));

    // Gols peladeiro2
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel2, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel2, partidasSalvo));
    golsPartidaRepository.saveAndFlush(new GolsPartidaModel(peladeiroModel2, partidasSalvo2));
    Long id = partidasSalvo.getId();

    List<GolsPartidaModel> todos = golsPartidaRepository.findByPartida(id);

    assertTrue(todos.size() > 4);
  }

  @Test
  @DisplayName("Deve buscar e apagar um golPartida pelo ID dela")
  void deleteById_quandoGolsPartidaExistir() {
    PeladeiroModel peladeiroSalvo =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.DESTRO));
    PartidasModel partidasSalvo = partidasRepository.saveAndFlush(new PartidasModel());

    GolsPartidaModel golsPartidaSalvo =
        golsPartidaRepository.save(new GolsPartidaModel(peladeiroSalvo, partidasSalvo));
    Long id = golsPartidaSalvo.getId();
    assertTrue(golsPartidaRepository.findById(id).isPresent());
    golsPartidaRepository.deleteById(id);
    assertTrue(golsPartidaRepository.findById(id).isEmpty());
  }
}
