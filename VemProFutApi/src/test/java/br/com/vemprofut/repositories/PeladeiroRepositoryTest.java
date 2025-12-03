package br.com.vemprofut.repositories;

import br.com.vemprofut.config.BaseIntegrationTest;
import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.services.implementacao.UploadLocalService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class PeladeiroRepositoryTest extends BaseIntegrationTest {
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
    @MockitoBean
    private OAuth2AuthorizedClientService authorizedClientService;

    // Opcional: Mocka o próprio Handler para evitar que ele tente rodar lógica
    @MockitoBean
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @MockitoBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @MockitoBean
    private UploadLocalService uploadLocalService;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Autowired PeladeiroRepository peladeiroRepository;

    @Test
    @DisplayName("deve gerar salvar um paladeiro e retornar um id")
    void deveSalvarPeladeiroNoMySQLReal(){
        PeladeiroModel peladeiroModel = new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                "Destro"
        );

        PeladeiroModel salvo = peladeiroRepository.save(peladeiroModel);

        assertNotNull(salvo.getId());
        assertTrue(salvo.getId() > 0);

    }

    @Test
    @DisplayName("findById deve retornar Optional vazio para id inexistente")
    void findByIdInexistente(){
        Optional<PeladeiroModel> encontrado = peladeiroRepository.findById(9999L);
        assertTrue(encontrado.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar uma lista de Peladeiro")
    void findAllDeveRetornaLista(){
        peladeiroRepository.saveAndFlush(new PeladeiroModel(
                "A", "a@test.com", "A", "333", "81555555555", "Destro"
        ));
        peladeiroRepository.saveAndFlush(new PeladeiroModel(
                "B", "b@test.com", "B", "444", "82000000000", "Destro"
        ));

        List<PeladeiroModel> todos = peladeiroRepository.findAll();
        assertTrue(todos.size() >= 2);
    }

    @Test
    @DisplayName("remover deve deletar um registro")
    void deleteDeveRemover(){
        PeladeiroModel p = peladeiroRepository.saveAndFlush(new PeladeiroModel("C", "c@test.com", "C", "", "3", "Destro"));
        Long id = p.getId();
        peladeiroRepository.deleteById(id);
        assertFalse(peladeiroRepository.findById(id).isPresent());
    }

    //TODO: testar existsByEmail e findByEmail ques estao no PeladeiroRepository.

}
