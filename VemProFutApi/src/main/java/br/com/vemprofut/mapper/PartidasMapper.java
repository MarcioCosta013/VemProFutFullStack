package br.com.vemprofut.mapper;

import br.com.vemprofut.DTOs.PartidasDTO;
import br.com.vemprofut.models.PartidasModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartidasMapper {

    //DTO -> Model
    @Mapping(target = "id_partida", source = "id")
    @Mapping(target = "jogadores_reservas", source = "reservas")
    @Mapping(target = "futModel", ignore = true)
    @Mapping(target = "cartoesModelPartida", ignore = true)
    @Mapping(target = "golsPartida", ignore = true)
    @Mapping(target = "peladeiros", ignore = true)
    PartidasModel toModel(PartidasDTO dto);

    //Model-> DTO
    @Mapping(target = "id", source = "id_partida")
    @Mapping(target = "reservas", source = "jogadores_reservas")
    @Mapping(target = "idFut", expression = "java(entity.getFutModel() != null ? entity.getFutModel().getId_fut() : null)")
    @Mapping(target = "golsPartida", expression = "java(entity.getGolsPartidaModel() != null ? entity.getGolsPartida().scream().map(g -> g.getId_fut()).toList() : null)")
    @Mapping(target = "peladeiros", expression = "java(entity.getPeladeiroModel() != null ? entity.getPeladeiroModel().scream().map( p -> p.getId_peladeiro()).toList() : null)") //você ignora o relacionamento reverso "cartoesModelPartida" para evitar loops de referência.
    PartidasDTO toDTO(PartidasModel model);
}
