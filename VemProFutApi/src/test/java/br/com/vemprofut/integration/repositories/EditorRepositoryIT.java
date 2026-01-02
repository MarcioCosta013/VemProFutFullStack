package br.com.vemprofut.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.EditorModel;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.models.enuns.PeDominante;
import br.com.vemprofut.repositories.EditorRepository;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.repositories.PeladeiroRepository;
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
public class EditorRepositoryIT {

  @MockitoBean private OAuth2AuthorizedClientService authorizedClientService;

  @MockitoBean private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @MockitoBean private ClientRegistrationRepository clientRegistrationRepository;

  @MockitoBean private UploadLocalService uploadLocalService;

  @MockitoBean private JwtDecoder jwtDecoder;

  @Autowired EditorRepository editorRepository;

  @Autowired PeladeiroRepository peladeiroRepository;

  @Autowired FutRepository futRepository;

  @Test
  @DisplayName("Deve salvar um novo Editor em um Fut especifico")
  void save_quandoEditorValido_retornaEditorSalvo() {

    FutModel futModel = futRepository.saveAndFlush(new FutModel());

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.CANHOTO));

    EditorModel editorModel =
        editorRepository.saveAndFlush(new EditorModel(peladeiroModel, futModel));

    assertNotNull(editorModel.getId());
    assertTrue(editorModel.getId() > 0);
  }

  @Test
  @DisplayName("Deve buscar e retornar um Editor salvo")
  void findById_quandoEditorExiste_retornaEditorSalvo() {
    FutModel futModel = futRepository.saveAndFlush(new FutModel());

    PeladeiroModel peladeiroModel =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                PeDominante.CANHOTO));

    EditorModel editorModel =
        editorRepository.saveAndFlush(new EditorModel(peladeiroModel, futModel));

    Long id = editorModel.getId();

    assertTrue(editorRepository.findById(id).isPresent());
  }
}
