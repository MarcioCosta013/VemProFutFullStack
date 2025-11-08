package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IMappersDefault.class})
public interface IFutMapper {

    //DTO --> Model
    FutModel toModel(FutDTO dto);

    //Model --> DTO
    FutDTO toDTO(FutModel model);
}
