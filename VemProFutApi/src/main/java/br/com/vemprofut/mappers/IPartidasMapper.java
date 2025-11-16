package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.response.SavePartidasResponseDTO;
import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.PartidasModel;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {IMappersDefault.class})
public interface IPartidasMapper {

  // DTO -> Model
  PartidasModel toModel(PartidasDTO dto);

  // Model-> DTO
  PartidasDTO toDTO(PartidasModel model);

  SavePartidasResponseDTO toResponse(PartidasModel model);

  List<SavePartidasResponseDTO> toResponseList(List<PartidasModel> models);
}
