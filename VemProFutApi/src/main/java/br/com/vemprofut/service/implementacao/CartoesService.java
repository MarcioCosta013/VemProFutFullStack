package br.com.vemprofut.service.implementacao;

import br.com.vemprofut.DTOs.CartoesDTO;
import br.com.vemprofut.DTOs.FutDTO;
import br.com.vemprofut.DTOs.PeladeiroDTO;
import br.com.vemprofut.service.ICartoesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartoesService implements ICartoesService {
    @Override
    public CartoesDTO criar(CartoesDTO dto) {
        return null;
    }

    @Override
    public List<CartoesDTO> buscarCartoesPeladeiro(PeladeiroDTO peladeiro) {
        return List.of();
    }

    @Override
    public List<CartoesDTO> buscarCartoesFut(FutDTO fut) {
        return List.of();
    }
}
