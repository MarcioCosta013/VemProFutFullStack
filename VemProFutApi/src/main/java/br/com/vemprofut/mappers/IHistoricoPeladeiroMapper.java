package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IHistoricoPeladeiroMapper {

    //DTO -> Model
    @Mapping(target = "id", source = "id")
    @Mapping(target = "golsHistoricoPeladeiro", source = "golsDoPeladeiro")
    @Mapping(target = "notaPeladeiro", source = "notaPeladeiro")
    @Mapping(target = "partidasJogadasHistorico", source = "partidasJogadas")
    @Mapping(target = "partidasGanhasHistorico", source = "partidasGanhas")
    HistoricoPeladeiroModel toModel(HistoricoPeladeiroDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "id")
    @Mapping(target = "golsDoPeladeiro", source = "golsPeladeiro")
    @Mapping(target = "notaPeladeiro", source = "notaPeladeiro")
    @Mapping(target = "partidasJogadas", source = "partidasJogadas")
    @Mapping(target = "partidasGanhas", source = "partidasGanhas")
    HistoricoPeladeiroDTO toDTO(HistoricoPeladeiroModel model);
}
