package br.com.vemprofut.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
public class BaseIntegrationTest {

    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    static void configureProperties(){
        mysql.start();

        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.username",mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());
        System.setProperty("spring.jpa.hibernate.ddl-auto", "none");
    }
}
/*
================= Classe base para todos os  testes de integração ===============
- Esse container MySQL sobe automaticamente ANTES do teste iniciar
- O Spring Boot usa o JDBC URL real do container
- Flyway será executado nas migrations automaticamente
 */
