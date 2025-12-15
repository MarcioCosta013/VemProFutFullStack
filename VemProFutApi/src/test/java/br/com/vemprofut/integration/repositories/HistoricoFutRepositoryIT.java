package br.com.vemprofut.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;
import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.HistoricoFutModel;
import br.com.vemprofut.repositories.HistoricoFutRepository;
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

import java.util.Optional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class HistoricoFutRepositoryIT {

    @MockitoBean private OAuth2AuthorizedClientService authorizedClientService;

    @MockitoBean private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @MockitoBean private ClientRegistrationRepository clientRegistrationRepository;

    @MockitoBean private UploadLocalService uploadLocalService;

    @MockitoBean private JwtDecoder jwtDecoder;

    @Autowired HistoricoFutRepository historicoFutRepository;

    @Test
    @DisplayName("Deve criar e salvar um novo HistoricoFutModel e retorna-lo com id")
    void save_quandoHistoricoFutValido_retornaHistoricoFutSalvo(){

        HistoricoFutModel historicoFutModel = new HistoricoFutModel();

        HistoricoFutModel salvo = historicoFutRepository.save(historicoFutModel);

        assertNotNull(salvo.getId());
        assertTrue(salvo.getId() > 0);
    }

    @Test
    @DisplayName("Deve buscar um HistoricoFutModel pelo id ")
    void findById_quandoHistoricoFutExiste_retornaHistoricoFutModel(){

        HistoricoFutModel historicoFutModel = new HistoricoFutModel();
        HistoricoFutModel salvo = historicoFutRepository.saveAndFlush(historicoFutModel);

        Long id = salvo.getId();

        assertNotNull(historicoFutRepository.findById(id));
    }

    @Test
    @DisplayName("Deve buscar um HistoricoFutModel pelo id ")
    void findById_quandoHistoricoFutInexistente_retornaNulo(){

        HistoricoFutModel historicoFutModel = new HistoricoFutModel();
        historicoFutRepository.saveAndFlush(historicoFutModel);

        assertEquals(Optional.empty(), historicoFutRepository.findById(9999L));
    }

    @Test
    @DisplayName("Deve verificar se um Historico Fut existe pelo ID")
    void existsById_quandoHistoricoFutExiste_RetornaTrue(){
        HistoricoFutModel historicoFutModel = new HistoricoFutModel();
        HistoricoFutModel salvo = historicoFutRepository.saveAndFlush(historicoFutModel);
        Long id = salvo.getId();

        assertTrue(historicoFutRepository.existsById(id));
    }

    @Test
    @DisplayName("Deve verificar se um Historico Fut existe pelo ID")
    void existsById_quandoHistoricoFutInexistente_RetornaFalse(){
        HistoricoFutModel historicoFutModel = new HistoricoFutModel();
        historicoFutRepository.saveAndFlush(historicoFutModel);

        assertFalse(historicoFutRepository.existsById(9999L));
    }

    @Test
    @DisplayName("Deve apagar um HistoricoFut pelo ID")
    void deleteById_quandoHistoricoFutExiste_semRetorno(){
        HistoricoFutModel historicoFutModel = new HistoricoFutModel();
        HistoricoFutModel salvo = historicoFutRepository.saveAndFlush(historicoFutModel);
        Long id = salvo.getId();

        assertTrue(historicoFutRepository.existsById(id));
        historicoFutRepository.deleteById(id);

        assertFalse(historicoFutRepository.existsById(id));
    }
}
