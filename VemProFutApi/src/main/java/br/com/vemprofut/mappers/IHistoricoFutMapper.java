package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.HistoricoFutDTO;
import br.com.vemprofut.models.HistoricoFutModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IHistoricoFutMapper {

    //DTO -> model
    @Mapping(target = "idHistoricoFut", source = "id")
    @Mapping(target = "golsTotalFut", source = "golsTotal")
    @Mapping(target = "totalPartidasJogadas", source = "totalPartidas")
    @Mapping(target = "timeMaisVitorias", source = "timeMaisVitorias")
    HistoricoFutModel toModel(HistoricoFutDTO dto);

    //Model -> DTO
    @Mapping(target = "id", source = "idHistoricoFut")
    @Mapping(target = "golsTotal", source = "golsTotalFut")
    @Mapping(target = "totalPartidas", source = "totalPartidasJogadas")
    @Mapping(target = "timeMaisVitorias", source = "timeMaisVitorias")
    HistoricoFutDTO toDTO(HistoricoFutModel model);
}
