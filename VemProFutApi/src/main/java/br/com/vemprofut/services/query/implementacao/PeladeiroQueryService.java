package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.EmailInUseException;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import org.springframework.stereotype.Service;

@Service
public class PeladeiroQueryService implements IPeladeiroQueryService {

    private PeladeiroRepository repository;

    @Override
    public void verifyEmail(String email) {
        if (repository.existsByEmail(email)) {
            var message = "O e-mail " + email + " já está em uso";
            throw new EmailInUseException(message);
        }
    }
}
