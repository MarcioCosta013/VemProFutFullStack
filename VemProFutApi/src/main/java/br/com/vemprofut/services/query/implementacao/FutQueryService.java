package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.FutInUseException;
import br.com.vemprofut.exceptions.NomeInUseException;
import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.repositories.FutRepository;
import br.com.vemprofut.services.query.IFutQueryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FutQueryService implements IFutQueryService {

    FutRepository repository;

    @Override
    public void verifyFutExist(Long dto) {
        if(repository.existsById(dto)){
            throw new FutInUseException("Fut já cadastrado!");
        }
    }

    @Override
    public FutModel verifyFutExistRetorn(Long fut) {
        return repository.findById(fut).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o Fut de id " + fut)
        );
    }

    @Override
    public void verifyNomeFutExist(String nome) {
        if(repository.existsByNomeStartingWith(nome)){
            throw new NomeInUseException("Nome de Fut já Cadastrado!");
        }
    }

    @Override
    public FutModel verifyNomeFutExistRetorn(String nome) {
        return repository.findByName(nome).orElseThrow(
                () -> new EntityNotFoundException("Futebol com nome '" + nome + "' não encontrado")
        );
    }
}
