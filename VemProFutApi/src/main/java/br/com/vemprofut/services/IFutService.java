package br.com.vemprofut.services;

import br.com.vemprofut.controllers.request.SaveFutRequestDTO;
import br.com.vemprofut.controllers.request.UpdateFutRequestDTO;
import br.com.vemprofut.controllers.response.FutDetailsResponse;
import br.com.vemprofut.controllers.response.SaveFutResponseDTO;
import br.com.vemprofut.controllers.response.UpdateFutResponseDTO;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.FutModel;

import java.util.List;

public interface IFutService {

    SaveFutResponseDTO create(SaveFutRequestDTO dto);

    FutDetailsResponse findById(Long id);

    FutModel findByIdModel(Long id);

    FutDTO findByNome(String nome);

    List<FutDTO> findAll();

    UpdateFutResponseDTO update(Long id, UpdateFutRequestDTO dto);

    void delete(Long id);

    void criarPartida(Boolean jogadoresReservas, FutModel futModel);

    void addPeladeiro(FutDTO dto, PeladeiroDTO peladeiroDTO);

    void addCartoes(FutDTO dto, CartoesDTO cartoesDTO);
}
