package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.GolsPartidaDTO;
import br.com.vemprofut.models.GolsPartidaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IGolsPartidaMapper {

    //Source --> é o objeto de origem, de onde vêm os dados
    //Target --> é o objeto de destino, para onde os dados vão

    //DTO -> Model
    @Mapping(target = "idGolsPartida", source = "id")
    @Mapping(target = "peladeiro", source = "peladeiro.idPeladeiro")
    @Mapping(target = "partida", source = "partida.idPartida")
    GolsPartidaModel toModel(GolsPartidaDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "idGolsPartida")
    @Mapping(target = "peladeiro.idPeladeiro", source = "peladeiro")
    @Mapping(target = "partida.idPartida", source = "partida")
    GolsPartidaDTO toDTO(GolsPartidaModel model);
}
