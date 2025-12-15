package br.com.vemprofut.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.repositories.PartidasRepository;
import br.com.vemprofut.services.implementacao.UploadLocalService;
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
public class PartidasRepositoryIT {

  @MockitoBean private OAuth2AuthorizedClientService authorizedClientService;

  @MockitoBean private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @MockitoBean private ClientRegistrationRepository clientRegistrationRepository;

  @MockitoBean private UploadLocalService uploadLocalService;

  @MockitoBean private JwtDecoder jwtDecoder;

  @Autowired PartidasRepository partidasRepository;

  @Test
  @DisplayName("Deve criar e salvar um nova partida")
  void save_quandoPartidaValida_retornaPartidaComID() {
    PartidasModel partidasModel = partidasRepository.save(new PartidasModel());

    assertNotNull(partidasModel.getId());
    assertTrue(partidasModel.getId() > 0);
  }

  @Test
  @DisplayName("Deve buscar uma Partida pelo ID dela")
  void findById_quandoExiste_retornaPartida() {
    PartidasModel partidasModel = partidasRepository.save(new PartidasModel());
    Long id = partidasModel.getId();

    assertTrue(partidasRepository.findById(id).isPresent());
  }

  @Test
  @DisplayName("Deve buscar uma Partida pelo ID dela e retornar Nulo")
  void findById_quandoInexistente_retornaNulo() {
    PartidasModel partidasModel = partidasRepository.save(new PartidasModel());

    assertTrue(partidasRepository.findById(9999L).isEmpty());
  }
}
