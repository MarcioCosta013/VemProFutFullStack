package br.com.vemprofut.service.implementacao;

import br.com.vemprofut.DTOs.CartoesDTO;
import br.com.vemprofut.exception.ResourceNotFoundException;
import br.com.vemprofut.mapper.CartoesMapper;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.repository.CartoesRepository;
import br.com.vemprofut.repository.PartidasRepository;
import br.com.vemprofut.repository.PeladeiroRepository;
import br.com.vemprofut.service.ICartoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartoesService implements ICartoesService {

    @Autowired
    private CartoesRepository cartoesRepository;

    @Autowired
    private PartidasRepository partidasRepository;

    @Autowired
    private PeladeiroRepository peladeiroRepository;

    @Autowired
    private CartoesMapper cartoesMapper;

    @Override
    @Transactional
    public CartoesDTO create(CartoesDTO cartoesDTO) {
        CartoesModel cartoesModel = cartoesMapper.toModel(cartoesDTO);
        updateRelationships(cartoesModel, cartoesDTO);
        CartoesModel savedCartoes = cartoesRepository.save(cartoesModel);
        return cartoesMapper.toDTO(savedCartoes);
    }

    @Override
    @Transactional(readOnly = true)
    public CartoesDTO findById(Long id) {
        CartoesModel cartoesModel = cartoesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cartões não encontrados com o id: " + id));
        return cartoesMapper.toDTO(cartoesModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartoesDTO> findAll() {
        return cartoesRepository.findAll().stream()
                .map(cartoesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CartoesDTO update(Long id, CartoesDTO cartoesDTO) {
        CartoesModel cartoesModel = cartoesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cartões não encontrados com o id: " + id));

        // Atualiza os campos simples
        cartoesModel.setAzuis_cartoes(cartoesDTO.cartoes_azul());
        cartoesModel.setAmarelos_cartoes(cartoesDTO.cartoes_amarelos());
        cartoesModel.setVermelhos_cartoes(cartoesDTO.cartoes_vermelhos());

        updateRelationships(cartoesModel, cartoesDTO);
        CartoesModel updatedCartoes = cartoesRepository.save(cartoesModel);
        return cartoesMapper.toDTO(updatedCartoes);
    }

    private void updateRelationships(CartoesModel model, CartoesDTO dto) {
        // Lógica para associar Partidas e Peladeiros
        // Esta parte do código assume que você tem os repositórios para Partidas e Peladeiros
    }
}