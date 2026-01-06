package br.com.vemprofut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class VemProFutApplication {

  private static final Logger log = LoggerFactory.getLogger(VemProFutApplication.class);

  public static void main(String[] args) {

    SpringApplication.run(VemProFutApplication.class, args);
    log.info("Aplicação iniciada com sucesso!");
  }
}
