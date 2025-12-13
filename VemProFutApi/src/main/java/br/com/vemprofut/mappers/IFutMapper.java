package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.request.SaveFutRequestDTO;
import br.com.vemprofut.controllers.response.FutDetailsResponse;
import br.com.vemprofut.controllers.response.SaveFutResponseDTO;
import br.com.vemprofut.controllers.response.UpdateFutResponseDTO;
import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {IMappersDefault.class})
public interface IFutMapper {

  // DTO --> Model
  @Mapping(target = "foto_url", ignore = true)
  @Mapping(target = "banidos", ignore = true)
  FutModel toModel(FutDTO dto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "foto_url", ignore = true)
  @Mapping(target = "historicoFutId", ignore = true)
  @Mapping(target = "editores", ignore = true)
  @Mapping(target = "peladeiros", ignore = true)
  @Mapping(target = "cartoes", ignore = true)
  @Mapping(target = "banidos", ignore = true)
  FutModel saveRequestToModel(SaveFutRequestDTO dto);

  SaveFutResponseDTO toSaveResponse(FutModel dto);

  FutDetailsResponse modelToDetailsResponse(FutModel model);

  UpdateFutResponseDTO modelToUpdateResponse(FutModel model);

  // Model --> DTO
  FutDTO toDTO(FutModel model);
}
