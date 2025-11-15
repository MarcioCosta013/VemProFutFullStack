package br.com.vemprofut.services.query.implementacao;

import br.com.vemprofut.exceptions.NotFoundException;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.repositories.CartoesRepository;
import br.com.vemprofut.services.query.ICartoesQueryService;
import org.springframework.stereotype.Service;

@Service
public class CartoesQueryService implements ICartoesQueryService {

    private CartoesRepository repository;

    @Override
    public void verifyPeladeiroExist(PeladeiroDTO peladeiroDTO) {
        //TODO: implementar
    }

    @Override
    public void verifyPartidasExist(PartidasDTO partidasDTO) {
        //TODO: implementar
    }

    // Verifica se o peladeiro, partida e fut existem
    public void verifyEntitiesExist(CartoesDTO dto) {
        if (!repository.existsById(dto.peladeiroId()))
            throw new IllegalArgumentException("Peladeiro não encontrado");

        if (!repository.existsById(dto.partidaId()))
            throw new IllegalArgumentException("Partida não encontrada");

        if (!repository.existsById(dto.futId()))
            throw new IllegalArgumentException("Fut não encontrado");
    }

    public CartoesModel verityCartoesExist (Long id){
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o Cartoes de id " + id)
        );
    }
}
