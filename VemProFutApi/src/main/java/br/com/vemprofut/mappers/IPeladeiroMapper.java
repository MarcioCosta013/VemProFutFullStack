package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.request.SavePeladeiroRequestDTO;
import br.com.vemprofut.controllers.request.UpdatePeladeiroRequestDTO;
import br.com.vemprofut.controllers.response.PeladeiroDetailResponse;
import br.com.vemprofut.controllers.response.SavePeladeiroResponseDTO;
import br.com.vemprofut.controllers.response.UpdatePeladeiroResponseDTO;
import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.services.implementacao.CartoesService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CartoesService.class })
public interface IPeladeiroMapper {

    // DTO --> Model
    @Mapping(target = "cartoes", ignore = true)
    @Mapping(target = "historicoPeladeiroModel", ignore = true) //TODO:Preenche manualmente no service
    @Mapping(target = "partidas", ignore = true)
    @Mapping(target = "cartoesModelPeladeiro", ignore = true)
    @Mapping(target = "participaFut", ignore = true)
    PeladeiroModel toModel(PeladeiroDTO dto);

    //Model --> DTO
    @Mapping(target = "cartoes", expression = "java(entity.getCartoesModel() != null ? entity.getCartoesModel().stream().map(c -> c.getIdCartoes()).toList() : null)")
    @Mapping(target = "historicoPeladeiro", expression = "java(entity.getHistoricoPeladeiroModel() != null ? entity.getHistoricoPeladeiroModel().getIdHistorico() : null)")
    @Mapping(target = "partidasIDs", expression = "java(entity.getPartidas() != null ? entity.getPartidas().stream().map(p -> p.getIdPartida()).toList() : null)")
    @Mapping(target = "futsIDs", expression = "java(entity.getParticipaFut() != null ? entity.getParticipaFut().stream().map(f -> f.getIdFut()).toList() : null)")
    PeladeiroDTO toDTO(PeladeiroModel model);

    @Mapping(
            target = "cartoes",
            expression = "java(cartoesService.contarCartoesPeladeiro(model.getId()))" //executa código Java para preencher o campo cartoes.
    )
    PeladeiroDetailResponse modelToDetailsDTO(PeladeiroModel model);

    PeladeiroModel saveRequestToModel(SavePeladeiroRequestDTO dto);

    UpdatePeladeiroResponseDTO modelToUpdateResponse(PeladeiroModel model);

    SavePeladeiroResponseDTO modelToSaveResponse(PeladeiroModel model);


}
