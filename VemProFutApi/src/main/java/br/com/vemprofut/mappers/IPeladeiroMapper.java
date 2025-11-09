package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.request.UpdatePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.UpdatePeladeiroResponseDTO;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.services.implementacao.CartoesService;
import br.com.vemprofut.services.implementacao.HistoricoPeladeiroService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CartoesService.class, IMappersDefault.class})
public interface IPeladeiroMapper {

    // DTO --> Model
    PeladeiroModel toModel(PeladeiroDTO dto);

    PeladeiroDetailResponse modelToDetailsDTO(PeladeiroModel model);

    @Mapping(target = "id", ignore = true)
    PeladeiroModel saveRequestToModel(SavePeladeiroRequestDTO dto);

    UpdatePeladeiroResponseDTO modelToUpdateResponse(PeladeiroModel model);

    SavePeladeiroResponseDTO modelToSaveResponse(PeladeiroModel model);

}
