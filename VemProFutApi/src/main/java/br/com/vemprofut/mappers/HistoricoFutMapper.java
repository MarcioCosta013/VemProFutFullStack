package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.HistoricoFutDTO;
import br.com.vemprofut.models.HistoricoFutModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoricoFutMapper {

    //DTO -> model
    @Mapping(target = "id_historico_fut", source = "id")
    @Mapping(target = "gols_total_fut", source = "golsTotal")
    @Mapping(target = "total_partidas_jogadas", source = "totalPartidas")
    @Mapping(target = "time_mais_vitorias", source = "timeMaisVitorias")
    HistoricoFutModel toModel(HistoricoFutDTO dto);

    //Model -> DTO
    @Mapping(target = "id", source = "id_historico_fut")
    @Mapping(target = "golsTotal", source = "gols_total_fut")
    @Mapping(target = "totalPartidas", source = "total_partidas_jogadas")
    @Mapping(target = "timeMaisVitorias", source = "time_mais_vitorias")
    HistoricoFutDTO toDTO(HistoricoFutModel model);
}
