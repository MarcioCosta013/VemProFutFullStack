package br.com.vemprofut.configs;

import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PeladeiroRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
  // Agora vamos interceptar o retorno do Auth0...

  private final PeladeiroRepository peladeiroRepository;
  private final OAuth2AuthorizedClientService authorizedClientService;

  @Override
  @Transactional
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {

    OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;

    String provider = authToken.getAuthorizedClientRegistrationId();

    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

    String email = oAuth2User.getAttribute("email");
    String name = oAuth2User.getAttribute("nome");
    String picture = oAuth2User.getAttribute("picture");

    PeladeiroModel usuario = peladeiroRepository.findByEmail(email);

    if (usuario == null) {
      usuario = new PeladeiroModel();
      usuario.setEmail(email);
      usuario.setNome(name);
      usuario.setApelido(name);
      usuario.setDescricao("Usuário criado via OAuth2");
      usuario.setWhatsapp("000000000");
      usuario.setPeDominante("Destro"); // escolha padrão
      usuario.setAuthProvider(provider);
      usuario.setFotoUrl(picture);
      peladeiroRepository.save(usuario);
    }

    response.sendRedirect("/bemvindo");
    // Cada usuário que logar via OAuth2 será criado no banco
  }
}
