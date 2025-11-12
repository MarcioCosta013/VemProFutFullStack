package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.EmailInUseException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.repositories.PeladeiroRepository;
import br.com.vemprofut.services.query.IPeladeiroQueryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PeladeiroQueryService implements IPeladeiroQueryService {

    private final PeladeiroRepository repository;

    @Override
    public void verifyEmail(String email) {
        if (repository.existsByEmail(email)) {
            var message = "O e-mail " + email + " já está em uso";
            throw new EmailInUseException(message);
        }
    }

    @Override
    public PeladeiroModel verifyPeladeiroExist(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o Peladeiro de id " + id)
        );
    }
}
