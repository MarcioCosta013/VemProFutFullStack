package br.com.vemprofut.config;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

@Testcontainers
@ActiveProfiles("test")
public class BaseIntegrationTest {

    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("root")
            .withEnv("MYSQL_ROOT_HOST", "root")
            .withCommand("--default-authentication-plugin=mysql_native_password")
            .withExposedPorts(3306)
            .withStartupTimeout(Duration.ofMinutes(5));


    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){

        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username",mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @BeforeAll
    static void showlog(){
        System.out.println(mysql.getLogs());
    }
}
/*
================= Classe base para todos os  testes de integração ===============
- Esse container MySQL sobe automaticamente ANTES do teste iniciar
- O Spring Boot usa o JDBC URL real do container
- Flyway será executado nas migrations automaticamente
- A base ficou abstract, como recomendado
- O container será iniciado apenas uma vez para toda a suíte
 */
