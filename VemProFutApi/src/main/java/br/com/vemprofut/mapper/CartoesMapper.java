package br.com.vemprofut.mapper;

import br.com.vemprofut.DTOs.CartoesDTO;
import br.com.vemprofut.models.CartoesModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") //diz ao MapStruct que essa interface é um mapper.
public interface CartoesMapper {
    //não precisa implementar, o Maptruct vai fazer isso sozinho.


    //CartoesMapper INSTANCE = Mappers.getMapper(CartoesMapper.class); <--- Ela cria uma instância estática do mapper, útil quando não estamos usando Spring.
    //vamos usar a injecao de dependencias entrão não vamos precisar da linha acima...

    @Mapping(target = "cartoesPartidas", ignore = true)//ignora na classe model
    @Mapping(target = "cartoesPeladeiros", ignore = true)
    CartoesModel toModel(CartoesDTO dto); // De DTO --> Entity/Model
    /*
    Esses dois campos (`cartoesPartida` e `cartoesPeladeiro`) são listas de objetos (`List<PartidasModel>` e
    `List<PeladeiroModel>`). Como o DTO não possui esses objetos (tem apenas os IDs), pedimos para **ignorar**
     o mapeamento automático.
    */

    @Mapping(target = "partidasIds", ignore = true)//ignora no DTO
    @Mapping(target = "peladeirosIds", ignore = true)
    CartoesDTO toDTO(CartoesModel model); //De Entity/Model --> DTO

    /*
    Os relacionamentos (List<PartidasModel>, List<PeladeiroModel>) são ignorados, porque o DTO só tem listas de IDs.
     */
}
