package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.PartidasModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IMappersDefault.class})
public interface IPartidasMapper {

    //DTO -> Model
    @Mapping(target = "id", source = "id")
    @Mapping(target = "jogadoresReservas", source = "reservas")
    @Mapping(target = "futModel", ignore = true)
    @Mapping(target = "cartoesModelPartida", ignore = true)
    @Mapping(target = "golsPartida", ignore = true)
    @Mapping(target = "peladeiros", ignore = true)
    @Mapping(target = "cartoes", ignore = true)
    PartidasModel toModel(PartidasDTO dto);

    //Model-> DTO
    @Mapping(target = "id", source = "id")
    @Mapping(target = "reservas", source = "jogadoresReservas")
    @Mapping(target = "futId", expression = "java(entity.getFutModel() != null ? entity.getFutModel().getIdFut() : null)")
    @Mapping(target = "golsPartida", expression = "java(entity.getGolsPartida() != null ? entity.getGolsPartida().stream().map(g -> g.getIdGol()).toList() : null)")
    @Mapping(target = "peladeiros", expression = "java(entity.getPeladeiros() != null ? entity.getPeladeiros().stream().map( p -> p.getIdPeladeiro()).toList() : null)") //você ignora o relacionamento reverso "cartoesModelPartida" para evitar loops de referência.
    @Mapping(target = "cartoes", expression = "java(entity.getCartoesModel() != null ? entity.getCartoesModel().stream().map(c -> c.getIdCartoes()).toList() : null)")
    PartidasDTO toDTO(PartidasModel model);
}
