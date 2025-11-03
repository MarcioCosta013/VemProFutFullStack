package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.GolsPartidaDTO;
import br.com.vemprofut.models.GolsPartidaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GolsPartidaMapper {

    //DTO -> Model
    @Mapping(target = "id_gols_partida", source = "id")
    @Mapping(target = "peladeiroModel", ignore = true)
    @Mapping(target = "partida", ignore = true)
    GolsPartidaModel toModel(GolsPartidaDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "id_gols_partida")
    @Mapping(target = "peladeiro", expression = "java(entity.getPeladeiroModel() != null ? entity.getPeladeiroModel().getId_peladeiro() : null)")
    @Mapping(target = "partida", expression = "java(entity.getPartidaModel() != null ? entity.getPartidaModel().getId_partida() : null)")
    GolsPartidaDTO toDTO(GolsPartidaModel model);
}
