package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.request.SaveFutRequestDTO;
import br.com.vemprofut.controllers.response.FutDetailsResponse;
import br.com.vemprofut.controllers.response.SaveFutResponseDTO;
import br.com.vemprofut.controllers.response.UpdateFutResponseDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IMappersDefault.class})
public interface IFutMapper {

    //DTO --> Model
    FutModel toModel(FutDTO dto);

    FutModel saveRequestToModel(SaveFutRequestDTO dto);

    SaveFutResponseDTO toSaveResponse(FutModel dto);

    FutDetailsResponse modelToDetailsResponse (FutModel model);

    UpdateFutResponseDTO modelToUpdateResponse (FutModel model);

    //Model --> DTO
    FutDTO toDTO(FutModel model);
}
