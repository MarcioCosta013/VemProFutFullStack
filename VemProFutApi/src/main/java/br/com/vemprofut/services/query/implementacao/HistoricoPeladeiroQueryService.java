package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import br.com.vemprofut.repositories.HistoricoPeladeiroRepository;
import br.com.vemprofut.services.query.IHistoricoPeladeiroQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoPeladeiroQueryService implements IHistoricoPeladeiroQueryService {
    @Autowired
    HistoricoPeladeiroRepository repository;

    @Override
    public HistoricoPeladeiroModel verityHistoricoPeladeiroExistReturn(Long id) {

        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o Historico de Peladeiro de id " + id)
        );
    }

    @Override
    public void verityHistoricoPeladeiroExist(Long id) {
        if(!repository.existsById(id)){
            throw new NotFoundException("Id: " + id + "de Historico Jogador não encontrado!");
        }
    }
}
