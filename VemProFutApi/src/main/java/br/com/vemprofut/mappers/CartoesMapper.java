package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.CartoesDTO;
import br.com.vemprofut.models.CartoesModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") //diz ao MapStruct que essa interface é um mapper.
public interface CartoesMapper {
    //não precisa implementar, o Maptruct vai fazer isso sozinho.


    //CartoesMapper INSTANCE = Mappers.getMapper(CartoesMapper.class); <--- Ela cria uma instância estática do mapper, útil quando não estamos usando Spring.
    //vamos usar a injecao de dependencias entrão não vamos precisar da linha acima...

    @Mapping(target = "id_cartoes", source = "id")
    @Mapping(target = "azuis_cartoes", source = "cartoes_azul")
    @Mapping(target = "amarelos_cartoes", source = "cartoes_amarelos")
    @Mapping(target = "vermelhos_cartoes", source = "cartoes_vermelhos")
    @Mapping(target = "cartoesPartidas", ignore = true)//ignora na classe model
    @Mapping(target = "cartoesPeladeiros", ignore = true)
    CartoesModel toModel(CartoesDTO dto); // De DTO --> Entity/Model
    /*
    Esses dois campos (`cartoesPartida` e `cartoesPeladeiro`) são listas de objetos (`List<PartidasModel>` e
    `List<PeladeiroModel>`). Como o DTO não possui esses objetos (tem apenas os IDs), pedimos para **ignorar**
     o mapeamento automático.
    */

    @Mapping(target = "id", source = "id_cartoes")
    @Mapping(target = "cartoes_azul", source = "azuis_cartoes")
    @Mapping(target = "cartoes_amarelos", source = "amarelos_cartoes")
    @Mapping(target = "cartoes_vermelhos", source = "vermelhos_cartoes")
    @Mapping(target = "partidasIds", expression = "java(entity.getCartoesPartidas() != null ? entity.getCartoesPartidas().stream().map(f -> f.getId_partida()).toList() : null")
    @Mapping(target = "peladeirosIds", expression = "java(entity.getCartoesPeladeiros() != null ? entity.getCartoesPeladeiros().stream().map(f -> f.id_peladeiro()).toList() : null)")
    CartoesDTO toDTO(CartoesModel model); //De Entity/Model --> DTO

}
