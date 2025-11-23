package br.com.vemprofut.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

  private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/", "/public/**").permitAll().anyRequest().authenticated())
        // OAuth2 login (Auth0)
        .oauth2Login(oauth2 -> oauth2.successHandler(oAuth2LoginSuccessHandler)) // login via OAuth2
        .oauth2ResourceServer(
            oauth2 -> oauth2.jwt(Customizer.withDefaults())); // aceitar JWT nas requisições

    return http.build();
  }
}
