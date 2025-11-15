package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.models.EditorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IMappersDefault.class})
public interface IEditorMapper {

    //DTO --> Model
    EditorModel toModel(EditorDTO dto);

    //Model --> DTO
    EditorDTO toDTO(EditorModel model);
}
