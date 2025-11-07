package br.com.vemprofut.services.implementacao;

import br.com.vemprofut.controllers.response.CartoesResumoResponseDTO;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.mappers.ICartoesMapper;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.enuns.CartaoCountProjection;
import br.com.vemprofut.repositories.CartoesRepository;
import br.com.vemprofut.services.ICartoesService;
import br.com.vemprofut.services.query.ICartoesQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartoesService implements ICartoesService {

    @Autowired
    private CartoesRepository repository;

    @Autowired
    private ICartoesMapper mapper;

    @Autowired
    private ICartoesQueryService queryService;

    @Override
    @Transactional
    public CartoesDTO create(CartoesDTO dto) {
        queryService.verifyEntitiesExist(dto);

        CartoesModel model = mapper.toModel(dto);
        CartoesModel saved = repository.save(model);
        return mapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartoesDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CartoesDTO findById(Long id) {
        return mapper.toDTO(queryService.verityCartoesExist(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartoesDTO> findByPeladeiro(Long id) {
        return repository.findByPeladeiroId(id)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartoesDTO> findByPartida(Long id) {
        return repository.findByPartidaId(id)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartoesDTO> findByFut(Long id) {
        return repository.findByFutId(id)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public CartoesResumoResponseDTO contarCartoesPeladeiro(Long peladeiroId){
        List<CartaoCountProjection> resultados = repository.countByTipoAndPeladeiro(peladeiroId);
        return montarResumo(resultados);
    }

    public CartoesResumoResponseDTO contarCartoesFut(Long futId){
        List<CartaoCountProjection> resultados = repository.countByTipoAndFut(futId);
        return montarResumo(resultados);
    }

    private CartoesResumoResponseDTO montarResumo(List<CartaoCountProjection> resultados) {
        CartoesResumoResponseDTO dto = new CartoesResumoResponseDTO();

        for (CartaoCountProjection proj : resultados) {
            switch (proj.getTipo()) {
                case AZUL -> dto.setAzul(proj.getQuantidade().intValue());
                case AMARELO -> dto.setAmarelo(proj.getQuantidade().intValue());
                case VERMELHO -> dto.setVermelho(proj.getQuantidade().intValue());
            }
        }

        return dto;
    }
}