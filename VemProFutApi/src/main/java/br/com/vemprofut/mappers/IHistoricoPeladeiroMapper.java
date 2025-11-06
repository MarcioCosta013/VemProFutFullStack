package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoricoPeladeiroMapper {

    //DTO -> Model
    @Mapping(target = "idHistoricoPeladeiro", source = "id")
    @Mapping(target = "golsHistoricoPeladeiro", source = "golsDoPeladeiro")
    @Mapping(target = "notaPeladeiro", source = "notaPeladeiro")
    @Mapping(target = "partidasJogadasHistorico", source = "partidasJogadas")
    @Mapping(target = "partidasGanhasHistorico", source = "partidasGanhas")
    HistoricoPeladeiroModel toModel(HistoricoPeladeiroDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "idHistoricoPeladeiro")
    @Mapping(target = "golsDoPeladeiro", source = "golsHistoricoPeladeiro")
    @Mapping(target = "notaPeladeiro", source = "notaPeladeiro")
    @Mapping(target = "partidasJogadas", source = "partidasJogadasHistorico")
    @Mapping(target = "partidasGanhas", source = "partidasGanhasHistorico")
    HistoricoPeladeiroDTO toDTO(HistoricoPeladeiroModel model);
}
