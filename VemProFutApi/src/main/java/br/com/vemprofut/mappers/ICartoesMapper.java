package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.CartoesModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") //diz ao MapStruct que essa interface é um mapper.
public interface ICartoesMapper {
    //não precisa implementar, o Maptruct vai fazer isso sozinho.


    //CartoesMapper INSTANCE = Mappers.getMapper(CartoesMapper.class); <--- Ela cria uma instância estática do mapper, útil quando não estamos usando Spring.
    //vamos usar a injecao de dependencias entrão não vamos precisar da linha acima...

    @Mapping(target = "id", source = "id")
    @Mapping(target = "partidaId", source = "partidaId")
    @Mapping(target = "peladeiroId", source = "peladeiroId")
    @Mapping(target = "futId", source = "futId")
    CartoesModel toModel(CartoesDTO dto); // De DTO --> Entity/Model
    /*
    Esses dois campos (`cartoesPartida` e `cartoesPeladeiro`) são listas de objetos (`List<PartidasModel>` e
    `List<PeladeiroModel>`). Como o DTO não possui esses objetos (tem apenas os IDs), pedimos para **ignorar**
     o mapeamento automático.
    */

    @Mapping(target = "id", source = "id")
    @Mapping(target = "partidaId", source = "partidaId")
    @Mapping(target = "peladeiroId", source = "peladeiroId")
    @Mapping(target = "futId", source = "futId")
    CartoesDTO toDTO(CartoesModel model); //De Entity/Model --> DTO

}
