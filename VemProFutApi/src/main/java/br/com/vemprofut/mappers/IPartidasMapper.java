package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.PartidasDTO;
import br.com.vemprofut.models.PartidasModel;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {IMappersDefault.class})
public interface IPartidasMapper {

  // DTO -> Model
  PartidasModel toModel(PartidasDTO dto);

  // Model-> DTO
  PartidasDTO toDTO(PartidasModel model);
}
