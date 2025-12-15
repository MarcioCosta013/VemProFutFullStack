package br.com.vemprofut.integration.repositories;

import static org.junit.jupiter.api.Assertions.*;
import br.com.vemprofut.configs.OAuth2LoginSuccessHandler;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.GolsPartidaRepository;
import br.com.vemprofut.repositories.PartidasRepository;
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
    void save_quandoGolPartidaValido_retornaGolPartidaSalvo(){
        PeladeiroModel peladeiroModel = new PeladeiroModel(
                "Marcio Teste", "teste@test.com", "Ronaldo", "o cara nota 10", "81992235678", "Destro"
        );
        PeladeiroModel peladeiroSalvo = peladeiroRepository.saveAndFlush(peladeiroModel);
        PartidasModel partidasModel = new PartidasModel();
        PartidasModel partidasSalvo = partidasRepository.saveAndFlush(partidasModel);

        GolsPartidaModel golsPartidaModel = new GolsPartidaModel(
                peladeiroSalvo,
                partidasSalvo
        );
        GolsPartidaModel golsPartidaSalvo = golsPartidaRepository.save(golsPartidaModel);

        assertNotNull(golsPartidaSalvo.getId());
        assertTrue(golsPartidaSalvo.getId() >0);
    }
}
