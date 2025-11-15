package br.com.vemprofut.mappers;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.CartoesDTO;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {IMappersDefault.class}) // diz ao MapStruct que essa interface é um mapper.
public interface ICartoesMapper { // Não precisa implementar, o Maptruct vai fazer isso sozinho.

  /*
  CartoesMapper INSTANCE = Mappers.getMapper(CartoesMapper.class); <--- Ela cria uma instância estática do mapper, útil quando não estamos usando Spring.
  vamos usar a injecao de dependencias entrão não vamos precisar da linha acima...
  */
  // De DTO --> Entity/Model
  CartoesModel toModel(CartoesDTO dto);

  /*
  Esses dois campos (`cartoesPartida` e `cartoesPeladeiro`) são listas de objetos (`List<PartidasModel>` e
  `List<PeladeiroModel>`). Como o DTO não possui esses objetos (tem apenas os IDs), pedimos para **ignorar**
   o mapeamento automático.
  */

  // De Entity/Model --> DTO
  CartoesDTO toDTO(CartoesModel model);
}
