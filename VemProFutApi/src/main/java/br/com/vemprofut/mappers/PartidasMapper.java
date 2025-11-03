package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.PartidasDTO;
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
    @Mapping(target = "golsPartida", expression = "java(entity.getGolsPartida() != null ? entity.getGolsPartida().stream().map(g -> g.getId_gol()).toList() : null)")
    @Mapping(target = "peladeiros", expression = "java(entity.getPeladeiros() != null ? entity.getPeladeiros().stream().map( p -> p.getId_peladeiro()).toList() : null)") //você ignora o relacionamento reverso "cartoesModelPartida" para evitar loops de referência.
    PartidasDTO toDTO(PartidasModel model);
}
