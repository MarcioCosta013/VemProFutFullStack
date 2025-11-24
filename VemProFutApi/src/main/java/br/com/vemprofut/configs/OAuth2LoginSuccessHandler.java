package br.com.vemprofut.configs;

import br.com.vemprofut.mappers.IHistoricoPeladeiroMapper;
import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.IHistoricoPeladeiroService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
  // Agora vamos interceptar o retorno do Auth0...

  private final PeladeiroRepository peladeiroRepository;
  private final OAuth2AuthorizedClientService authorizedClientService;
  private final IHistoricoPeladeiroService historicoPeladeiroService;
  private final IHistoricoPeladeiroMapper historicoPeladeiroMapper;

  @Override
  @Transactional
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {

    OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

    log.info("OAuth2 Attributes: {}", oAuth2User.getAttributes());
    log.info("Authorities: {}", oAuth2User.getAuthorities());
    log.info("User Name Attribute: {}", oAuth2User.getName());

    String provider = authToken.getAuthorizedClientRegistrationId();

    String email = oAuth2User.getAttribute("email");
    String name = oAuth2User.getAttribute("name");
    String picture = oAuth2User.getAttribute("picture");

    log.info("Email recebido: {}", email);
    log.info("Nome recebido: {}", name);
    log.info("Foto recebida: {}", picture);

    PeladeiroModel usuario = peladeiroRepository.findByEmail(email);

    if (usuario == null) {
      log.info("Usuario inesxistente, cadastrando novo usuario...");
      HistoricoPeladeiroDTO historicoPeladeiro = historicoPeladeiroService.create();
      usuario = new PeladeiroModel();
      usuario.setEmail(email);
      usuario.setNome(name);
      usuario.setApelido(name);
      usuario.setDescricao("Usuário criado via OAuth2");
      usuario.setWhatsapp("000000000");
      usuario.setPeDominante("Destro"); // escolha padrão
      usuario.setAuthProvider(provider);
      usuario.setFotoUrl(picture);
      usuario.setHistoricoPeladeiro(historicoPeladeiroMapper.toModel(historicoPeladeiro));
      peladeiroRepository.save(usuario);

      log.info("Usuário salvo no banco com ID {}", usuario.getId());
    } else {
      log.info("Usuario encontrado! ID: {}", usuario.getId());
    }

    response.sendRedirect("/bemvindo");
    // Cada usuário que logar via OAuth2 será criado no banco
  }
}
