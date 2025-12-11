package br.com.vemprofut.repositories;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.services.implementacao.UploadLocalService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
public class FutRepositoryTest {

  @MockitoBean private OAuth2AuthorizedClientService authorizedClientService;

  @MockitoBean private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @MockitoBean private ClientRegistrationRepository clientRegistrationRepository;

  @MockitoBean private UploadLocalService uploadLocalService;

  @MockitoBean private JwtDecoder jwtDecoder;

  @Autowired FutRepository futRepository;

  @Test
  // @DisplayName("deve gerar um Fut e retornar um id")
  public void deveSalvarFut() {

    FutModel futModel = new FutModel("Fut teste", 4, 10, 2, null, null);

    FutModel salvo = futRepository.save(futModel);

    assertNotNull(salvo.getId());
    assertTrue(salvo.getId() > 0);
  }

  @Test
  // @DisplayName("findById deve retornar Optional vazio para id inexistente")
  public void findByIdInexistente() {
    Optional<FutModel> encontrado = futRepository.findById(9999L);
    assertTrue(encontrado.isEmpty());
  }

  @Test
  // @DisplayName("Deve retornar uma lista de Peladeiro")
  public void findAllDeveRetornaListaFut() {
    futRepository.saveAndFlush(new FutModel("Fut teste1", 4, 10, 2, null, null));
    futRepository.saveAndFlush(new FutModel("Fut teste2", 4, 10, 2, null, null));

    List<FutModel> lista = futRepository.findAll();
    assertTrue(lista.size() >= 2);
  }

  @Test
  // @DisplayName("deve deletar um registro")
  public void deleteFut() {
    FutModel f = futRepository.saveAndFlush(new FutModel("Fut teste1", 4, 10, 2, null, null));

    Long id = f.getId();
    futRepository.deleteById(id);
    assertFalse(futRepository.findById(id).isPresent());
  }

  // TODO: implementar os testes de Fut buscarFutComListPeladeiros e buscarFutComListEditores
}
