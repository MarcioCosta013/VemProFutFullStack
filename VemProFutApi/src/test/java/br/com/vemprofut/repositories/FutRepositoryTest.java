package br.com.vemprofut.repositories;

import br.com.vemprofut.config.BaseIntegrationTest;
import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.services.implementacao.UploadLocalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

public class FutRepositoryTest extends BaseIntegrationTest {

    @MockitoBean
    private OAuth2AuthorizedClientService authorizedClientService;

    @MockitoBean
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @MockitoBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @MockitoBean
    private UploadLocalService uploadLocalService;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Autowired FutRepository futRepository;

    @Test
    @DisplayName("deve gerar salvar um Fut e retornar um id")
    void deveSalvarFut(){

    }

    //TODO: implementar os testes de Fut
}
