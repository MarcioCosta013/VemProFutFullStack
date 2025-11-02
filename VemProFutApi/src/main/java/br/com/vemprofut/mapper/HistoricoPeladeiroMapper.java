package br.com.vemprofut.mapper;

import br.com.vemprofut.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoricoPeladeiroMapper {

    //DTO -> Model
    @Mapping(target = "id_historico_peladeiro", source = "id")
    @Mapping(target = "gols_historico_peladeiro", source = "golsDoPeladeiro")
    @Mapping(target = "nota_peladeiro", source = "notaPeladeiro")
    @Mapping(target = "partidas_jogadas_historico", source = "partidasJogadas")
    @Mapping(target = "partidas_ganhas_historico", source = "partidasGanhas")
    HistoricoPeladeiroModel toModel(HistoricoPeladeiroDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "id_historico_peladeiro")
    @Mapping(target = "golsDoPeladeiro", source = "gols_historico_peladeiro")
    @Mapping(target = "notaPeladeiro", source = "nota_peladeiro")
    @Mapping(target = "partidasJogadas", source = "partidas_jogadas_historico")
    @Mapping(target = "partidasGanhas", source = "partidas_ganhas_historico")
    HistoricoPeladeiroDTO toDTO(HistoricoPeladeiroModel model);
}
