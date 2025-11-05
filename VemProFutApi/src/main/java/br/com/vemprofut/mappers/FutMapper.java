package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FutMapper {

    //DTO --> Model
    @Mapping(target = "idFut", source = "id")
    @Mapping(target = "nomeFut", source = "nome")
    @Mapping(target = "jogadoresPorTime", source = "jogadoresPorTime")
    @Mapping(target = "tempoMaxPartida", source = "tempoMaxPartida")
    @Mapping(target = "maxGolsVitoria", source = "maxGolsPartida")
    @Mapping(target = "historicoFutModel", ignore = true)
    @Mapping(target = "administradorPeladeiro", ignore = true)
    @Mapping(target = "peladeiros", ignore = true)
    @Mapping(target = "cartoes", ignore = true)
    FutModel toModel(FutDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "idFut")
    @Mapping(target = "nome", source = "nomeFut")
    @Mapping(target = "jogadoresPorTime", source = "jogadoresPorTime")
    @Mapping(target = "tempoMaxPartida", source = "tempoMaxPartida")
    @Mapping(target = "maxGolsPartida", source = "maxGolsVitoria")
    @Mapping(target = "historicoFutId", expression = "java(entity.getHistoricoFutModel() != null ? entity.getHistoricoFutModel().getIdHistoricoFut(): null)" )
    @Mapping(target = "admPeladeiroId", expression = "java(entity.getAdministradorPeladeiro() != null ? entity.getAdministradorPeladeiro().getIdPeladeiro : null)")
    @Mapping(target = "peladeiros", expression = "java(entity.getParticipaFut() != null ? entity.getParticipaFut().stream().map(f -> f.getIdFut()).toList() : null)")
    @Mapping(target = "cartoes", expression = "java(entity.getCartoesModel() != null ? entity.getCartoesModel().stream().map(c -> c.getIdCartoes()).toList() : null)")
    FutDTO toDTO(FutModel model);
}
