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
    @Mapping(target = "idPeladeiro", expression = "java(entity.getPeladeiroModel() != null ? entity.getPeladeiroModel().getId_peladeiro() : null)")
    @Mapping(target = "idFut", expression = "java(entity.getFutModel() != null ?  entity.getFutModel().getId_fut() : null)")
    EditorDTO toDTO(EditorModel model);
}
