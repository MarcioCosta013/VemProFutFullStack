package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.EditorDTO;
import br.com.vemprofut.models.EditorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IEditorMapper {

    //DTO --> Model
    @Mapping(target = "id", source = "id")
    @Mapping(target = "peladeiroModel.idPeladeiro", source = "peladeiroId")
    @Mapping(target = "futModel.futId", source = "futId")
    EditorModel toModel(EditorDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "id")
    @Mapping(target = "idPeladeiro",source = "peladeiroModel.idPeladeiro")
    @Mapping(target = "futId", source = "futModel.futId")
    EditorDTO toDTO(EditorModel model);
}
