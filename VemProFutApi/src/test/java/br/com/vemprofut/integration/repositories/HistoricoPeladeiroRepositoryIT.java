package br.com.vemprofut.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import br.com.vemprofut.repositories.HistoricoPeladeiroRepository;
import br.com.vemprofut.services.implementacao.UploadLocalService;
import java.util.Optional;
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
public class HistoricoPeladeiroRepositoryIT {

  @MockitoBean private OAuth2AuthorizedClientService authorizedClientService;

  @MockitoBean private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @MockitoBean private ClientRegistrationRepository clientRegistrationRepository;

  @MockitoBean private UploadLocalService uploadLocalService;

  @MockitoBean private JwtDecoder jwtDecoder;

  @Autowired HistoricoPeladeiroRepository historicoPeladeiroRepository;

  @Test
  @DisplayName("Deve criar e salvar um novo HistoricoPeladeiro")
  void save_quandoHistoricoPeladeiroValido() {
    HistoricoPeladeiroModel historicoPeladeiroModel = new HistoricoPeladeiroModel();

    HistoricoPeladeiroModel salvo = historicoPeladeiroRepository.save(historicoPeladeiroModel);

    assertNotNull(salvo.getId());
    assertTrue(salvo.getId() > 0);
  }

  @Test
  @DisplayName("Deve buscar um HistoricoFutModel pelo id ")
  void findById_quandoHistoricoFutExiste_retornaHistoricoFutModel() {

    HistoricoPeladeiroModel historicoPeladeiroModel = new HistoricoPeladeiroModel();
    HistoricoPeladeiroModel salvo =
        historicoPeladeiroRepository.saveAndFlush(historicoPeladeiroModel);

    Long id = salvo.getId();

    assertNotNull(historicoPeladeiroRepository.findById(id));
  }

  @Test
  @DisplayName("Deve buscar um HistoricoFutModel pelo id ")
  void findById_quandoHistoricoFutInexistente_retornaNulo() {

    HistoricoPeladeiroModel historicoPeladeiroModel = new HistoricoPeladeiroModel();
    historicoPeladeiroRepository.saveAndFlush(historicoPeladeiroModel);

    assertEquals(Optional.empty(), historicoPeladeiroRepository.findById(9999L));
  }

  @Test
  @DisplayName("Deve verificar se um Historico Fut existe pelo ID")
  void existsById_quandoHistoricoFutExiste_RetornaTrue() {
    HistoricoPeladeiroModel historicoPeladeiroModel = new HistoricoPeladeiroModel();
    HistoricoPeladeiroModel salvo =
        historicoPeladeiroRepository.saveAndFlush(historicoPeladeiroModel);
    Long id = salvo.getId();

    assertTrue(historicoPeladeiroRepository.existsById(id));
  }

  @Test
  @DisplayName("Deve verificar se um Historico Fut existe pelo ID")
  void existsById_quandoHistoricoFutInexistente_RetornaFalse() {
    HistoricoPeladeiroModel historicoPeladeiroModel = new HistoricoPeladeiroModel();
    historicoPeladeiroRepository.saveAndFlush(historicoPeladeiroModel);

    assertFalse(historicoPeladeiroRepository.existsById(9999L));
  }

  @Test
  @DisplayName("Deve apagar um HistoricoFut pelo ID")
  void deleteById_quandoHistoricoFutExiste_semRetorno() {
    HistoricoPeladeiroModel historicoPeladeiroModel = new HistoricoPeladeiroModel();
    HistoricoPeladeiroModel salvo =
        historicoPeladeiroRepository.saveAndFlush(historicoPeladeiroModel);
    Long id = salvo.getId();

    assertTrue(historicoPeladeiroRepository.existsById(id));
    historicoPeladeiroRepository.deleteById(id);

    assertFalse(historicoPeladeiroRepository.existsById(id));
  }
}
