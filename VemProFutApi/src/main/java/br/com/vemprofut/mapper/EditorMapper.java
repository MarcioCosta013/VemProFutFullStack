package br.com.vemprofut.mapper;

import br.com.vemprofut.DTOs.EditorDTO;
import br.com.vemprofut.models.EditorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EditorMapper {

    //DTO --> Model
    @Mapping(target = "id_editor", source = "id")
    @Mapping(target = "peladeiroModel", ignore = true)
    @Mapping(target = "futModel", ignore = true)
    EditorModel toModel(EditorDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "id_editor")
    @Mapping(target = "idPeladeiro", ignore = true)
    @Mapping(target = "idFut", ignore = true)
    EditorDTO toDTO(EditorModel model);
}
