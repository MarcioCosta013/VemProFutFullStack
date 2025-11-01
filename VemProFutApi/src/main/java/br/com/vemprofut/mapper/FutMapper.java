package br.com.vemprofut.mapper;

import br.com.vemprofut.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FutMapper {

    //DTO --> Model
    @Mapping(target = "id_fut")
    @Mapping(target = "nome_fut")
    @Mapping(target = "jogadores_por_time")
    @Mapping(target = "tempo_max_partida")
    @Mapping(target = "max_gols_vitoria")
    @Mapping(target = "historicoFutModel", ignore = true)
    @Mapping(target = "administradorPeladeiro", ignore = true)
    @Mapping(target = "peladeiros", ignore = true)
    FutModel toModel(FutDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "id_fut")
    @Mapping(target = "nome", source = "nome_fut")
    @Mapping(target = "jogadoresPorTime", source = "jogadores_por_time")
    @Mapping(target = "tempoMaxPartida", source = "tempo_max_partida")
    @Mapping(target = "maxGolsPartida", source = "max_gols_vitoria")
    @Mapping(target = "historicoFutId", expression = "java(entity.getHistoricoFutModel() != null ? entity.getHistoricoFutModel().getId_historico_fut(): null)" )
    @Mapping(target = "admPeladeiroId", expression = "java(entity.getAdministradorPeladeiro() != null ? entity.getAdministradorPeladeiro().getId_peladeiro : null)")
    @Mapping(target = "peladeiros", expression = "java(entity.getParticipaFut() != null ? entity.getParticipaFut().stream().map(f -> f.getId_fut()).toList() : null)")
    FutDTO toDTO(FutModel model);
}
