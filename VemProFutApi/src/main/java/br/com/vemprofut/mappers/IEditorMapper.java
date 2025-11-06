package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.models.EditorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IEditorMapper {

    //DTO --> Model
    @Mapping(target = "idEditor", source = "id")
    @Mapping(target = "peladeiroModel.idPeladeiro", source = "peladeiroModel")
    @Mapping(target = "futModel.idFut", source = "futModel")
    EditorModel toModel(EditorDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "idEditor")
    @Mapping(target = "idPeladeiro",source = "peladeiroModel.idPeladeiro")
    @Mapping(target = "idFut", source = "futModel.idFut")
    EditorDTO toDTO(EditorModel model);
}
