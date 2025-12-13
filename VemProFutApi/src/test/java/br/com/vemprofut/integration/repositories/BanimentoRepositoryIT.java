package br.com.vemprofut.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.BanimentoModel;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.BanimentoRepository;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.implementacao.UploadLocalService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
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

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class BanimentoRepositoryIT {

  @MockitoBean private OAuth2AuthorizedClientService authorizedClientService;

  // Opcional: Mocka o próprio Handler para evitar que ele tente rodar lógica
  @MockitoBean private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @MockitoBean private ClientRegistrationRepository clientRegistrationRepository;

  @MockitoBean private JwtDecoder jwtDecoder;

  @MockitoBean private UploadLocalService uploadLocalService;

  @Autowired BanimentoRepository banimentoRepository;

  @Autowired PeladeiroRepository peladeiroRepository;

  @Autowired FutRepository futRepository;

  @Test
  @DisplayName("Deve gerar salvar um banimento e retornar um id")
  public void save_quandoBanimentoValido_retornaIdGerado() {
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

    BanimentoModel banimentoModel =
        new BanimentoModel(
            "quebrou a perna do amigo",
            LocalDate.of(2025, 11, 9),
            LocalDate.of(2026, 11, 9),
            peladeiroModel,
            futModel);

    BanimentoModel banimentoSalvo = banimentoRepository.save(banimentoModel);

    assertNotNull(banimentoSalvo.getId());
    assertTrue(banimentoSalvo.getId() > 0);
  }

  @Test
  @DisplayName("Deve buscar todos os os banimentos")
  public void findAll_quandoExistemRegistros_retornaListaComTodos() {
    FutModel futModel1 = futRepository.saveAndFlush(new FutModel());
    FutModel futModel2 = futRepository.saveAndFlush(new FutModel());
    PeladeiroModel peladeiroModel1 =
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
                "Apelido2",
                "descricao qualquer2",
                "81999999999",
                "Destro"));

    BanimentoModel banimentoModel1 =
        new BanimentoModel(
            "quebrou a perna do amigo",
            LocalDate.of(2025, 11, 9),
            LocalDate.of(2026, 11, 9),
            peladeiroModel1,
            futModel1);
    BanimentoModel banimentoModel2 =
        new BanimentoModel(
            "quebrou o ombro do amigo",
            LocalDate.of(2025, 11, 9),
            LocalDate.of(2026, 11, 9),
            peladeiroModel2,
            futModel2);

    banimentoRepository.saveAndFlush(banimentoModel1);
    banimentoRepository.saveAndFlush(banimentoModel2);

    List<BanimentoModel> todos = banimentoRepository.findAll();

    assertTrue(todos.size() >= 2);
  }

  @Test
  @DisplayName("Deve apagar um banimento pelo numero do ID")
  public void deleteById_quandoIdBanimentoExistir_registroRemovido() {
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

    BanimentoModel banimentoModel =
        new BanimentoModel(
            "quebrou a perna do amigo",
            LocalDate.of(2025, 11, 9),
            LocalDate.of(2026, 11, 9),
            peladeiroModel,
            futModel);

    BanimentoModel banimentoSalvo = banimentoRepository.saveAndFlush(banimentoModel);

    Long id = banimentoSalvo.getId();

    banimentoRepository.deleteById(id);
    assertFalse(banimentoRepository.findById(id).isPresent());
  }
}
