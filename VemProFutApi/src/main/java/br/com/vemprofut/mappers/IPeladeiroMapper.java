package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.PeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.UpdatePeladeiroResponseDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.services.implementacao.CartoesService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {CartoesService.class, IMappersDefault.class})
public interface IPeladeiroMapper {

  // DTO --> Model
  PeladeiroModel toModel(PeladeiroDTO dto);

  @BeanMapping(
      ignoreUnmappedSourceProperties = {
        "partidas",
        "futs",
        "administra",
        "editores",
        "golsPeladeiro",
        "historicoPeladeiro"
      })
  PeladeiroDetailResponse modelToDetailsDTO(PeladeiroModel model);

  @Mapping(target = "id", ignore = true)
  PeladeiroModel saveRequestToModel(SavePeladeiroRequestDTO dto);

  UpdatePeladeiroResponseDTO modelToUpdateResponse(PeladeiroModel model);

  SavePeladeiroResponseDTO modelToSaveResponse(PeladeiroModel model);

  @Mapping(source = "id", target = "peladeiroId")
  PeladeiroResponseDTO modelToPeladeiroResponse(PeladeiroModel model);
}
