package br.com.vemprofut.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;

import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.models.enuns.PeDominante;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.implementacao.UploadLocalService;
import jakarta.transaction.Transactional;
import java.util.List;
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

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class PeladeiroRepositoryIT {
  /*
  - Isso roda usando MySQL real via Docker
  - Isso roda com Flyway
  - Sem mocks — integração real

  Porque:
     - Garante que o código realmente funciona no mesmo banco da produção.
     - Evita problemas de comportamento diferente entre H2 <-> MySQL.
     - Torna o ambiente de Dev ≈ Prod.
   */
  // Mocka o serviço que o Spring não está encontrando
  /*
  Ao usar o @MockitoBean, você diz ao Spring: Não se preocupe em criar essa dependência real;
  use um objeto de simulação (mock) no lugar para que o contexto possa ser inicializado.
   */
  @MockitoBean private OAuth2AuthorizedClientService authorizedClientService;

  // Opcional: Mocka o próprio Handler para evitar que ele tente rodar lógica
  @MockitoBean private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @MockitoBean private ClientRegistrationRepository clientRegistrationRepository;

  @MockitoBean private UploadLocalService uploadLocalService;

  @MockitoBean private JwtDecoder jwtDecoder;

  @Autowired PeladeiroRepository peladeiroRepository;

  @Test
  @DisplayName("Deve gerar salvar um paladeiro e retornar um id")
  public void save_quandoPeladeiroValido_retornaIdGerado() {
    PeladeiroModel peladeiroModel =
        new PeladeiroModel(
            "Marcio Teste",
            "teste@test.com",
            "Ronaldo",
            "o cara nota 10",
            "81992235678",
            PeDominante.DESTRO);

    PeladeiroModel salvo = peladeiroRepository.save(peladeiroModel);

    assertNotNull(salvo.getId());
    assertTrue(salvo.getId() > 0);
  }

  @Test
  @DisplayName("findById deve retornar Optional vazio para id inexistente")
  public void findById_quandoIdInexistente_retornaOptionalVazio() {
    Optional<PeladeiroModel> encontrado = peladeiroRepository.findById(9999L);
    assertTrue(encontrado.isEmpty());
  }

  @Test
  @DisplayName("findAll deve retornar lista com todos os Peladeiros")
  public void findAll_quandoExistemRegistros_retornaListaComTodos() {
    peladeiroRepository.saveAndFlush(
        new PeladeiroModel("A", "a@test.com", "A", "333", "81555555555", PeDominante.DESTRO));
    peladeiroRepository.saveAndFlush(
        new PeladeiroModel("B", "b@test.com", "B", "444", "82000000000", PeDominante.DESTRO));

    List<PeladeiroModel> todos = peladeiroRepository.findAll();
    assertTrue(todos.size() >= 2);
  }

  @Test
  @DisplayName("deleteById deve remover um registro existente")
  public void deleteById_quandoIdExistente_registroEhRemovido() {
    PeladeiroModel p =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel("C", "c@test.com", "C", "", "3", PeDominante.DESTRO));
    Long id = p.getId();
    peladeiroRepository.deleteById(id);
    assertFalse(peladeiroRepository.findById(id).isPresent());
  }

  @Test
  @DisplayName("deve retorna de o email ja foi castrado ou nao")
  public void existsByEmail_quandoEmailCadastrado_retornaTrue() {
    peladeiroRepository.saveAndFlush(
        new PeladeiroModel("C", "test@test.com", "C", "", "3", PeDominante.DESTRO));
    String email = "test@test.com";
    Boolean exist = peladeiroRepository.existsByEmail(email);

    assertTrue(exist);
  }

  @Test
  @DisplayName("deve retorna falso de o email ja foi castrado ou nao")
  public void existsByEmail_quandoEmailNaoCadastrado_retornaFalse() {
    peladeiroRepository.saveAndFlush(
        new PeladeiroModel("C", "test@test.com", "C", "", "3", PeDominante.DESTRO));
    String email = "test2@test.com";
    Boolean exist = peladeiroRepository.existsByEmail(email);

    assertFalse(exist);
  }

  @Test
  // @DisplayName("Deve retornar um Peladeiro ao pesquisar pelo email")
  public void buscarPeladeiroPeloEmailRetornaPeladeiro() {
    PeladeiroModel p =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "C", "test@test.com", "C", "descricao", "377777777777", PeDominante.DESTRO));
    String email = "test@test.com";
    PeladeiroModel peladeiroModel = peladeiroRepository.findByEmail(email);

    assertEquals(p.getNome(), peladeiroModel.getNome());
    assertEquals(p.getEmail(), peladeiroModel.getEmail());
    assertEquals(p.getApelido(), peladeiroModel.getApelido());
    assertEquals(p.getWhatsapp(), peladeiroModel.getWhatsapp());
    assertEquals(p.getDescricao(), peladeiroModel.getDescricao());
    assertEquals(p.getPeDominante(), peladeiroModel.getPeDominante());
  }

  @Test
  // @DisplayName("Deve retornar null ao buscar um peladeiro pelo email")
  public void buscarPeladeiroPeloEmailRetornaNull() {
    PeladeiroModel p =
        peladeiroRepository.saveAndFlush(
            new PeladeiroModel(
                "C", "test@test.com", "C", "descricao", "377777777777", PeDominante.DESTRO));
    String email = "test1@test.com";
    PeladeiroModel peladeiroModel = peladeiroRepository.findByEmail(email);

    assertNull(peladeiroModel);
  }
}
