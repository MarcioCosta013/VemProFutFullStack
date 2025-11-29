package br.com.vemprofut.repositories;

import br.com.vemprofut.config.BaseIntegrationTest;
import br.com.vemprofut.models.PeladeiroModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class PeladeiroRepositoryTest extends BaseIntegrationTest {

    @Autowired PeladeiroRepository peladeiroRepository;

    @Test
    void deveSalvarPeladeiroNoMySQLReal(){
        PeladeiroModel peladeiroModel = new PeladeiroModel(
                "Marcio Teste",
                "teste@test.com",
                "Ronaldo",
                "o cara nota 10",
                "81992235678",
                "Destro"
        );

        PeladeiroModel salvo = peladeiroRepository.save(peladeiroModel);

        assertNotNull(salvo.getId());

    }
}
/*
- Isso roda usando MySQL real via Docker
- Isso roda com Flyway
- Sem mocks — integração real

Porque:
   - Garante que o código realmente funciona no mesmo banco da produção.
   - Evita problemas de comportamento diferente entre H2 <-> MySQL.
   - Torna o ambiente de Dev ≈ Prod.
 */