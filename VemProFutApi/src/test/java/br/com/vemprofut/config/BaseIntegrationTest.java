package br.com.vemprofut.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

    // Configuração do Container MySQL
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass")
            // Aumenta o tempo limite para 5 minutos (para evitar o erro atual)
            .withStartupTimeout(Duration.ofMinutes(5))
            // Mapeia a pasta de dados do MySQL para a memória RAM (tmpfs).
            // Isso resolve o problema de lentidão de disco no Windows/Docker.
            .withTmpFs(Collections.singletonMap("/var/lib/mysql", "rw"))
            // Comandos para desativar segurança de gravação (fsync) e acelerar o boot
            .withCommand(
                    "--character-set-server=utf8mb4",
                    "--collation-server=utf8mb4_unicode_ci",
                    "--innodb_flush_log_at_trx_commit=2", // Não faz sync no disco a cada transação
                    "--sync_binlog=0", // Desativa sync do binlog
                    "--skip-log-bin"   // Desativa log binário (não precisa pra teste simples)
            );

    // Inicia o container uma vez para toda a suíte de testes
    static {
        mySQLContainer.start();
    }

    // Configuração dinâmica do Spring para usar o container
    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        // Flyway será configurado para rodar as migrações no container recém-iniciado
        registry.add("spring.flyway.enabled", () -> true);
    }

//    @Autowired
//    protected TestRestTemplate restTemplate; //Mockar nostestes de controllers depois

    // ... outros Autowired (Repositories, Services)
}
