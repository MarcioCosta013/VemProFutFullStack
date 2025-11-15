package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.GolsPartidaDTO;
import br.com.vemprofut.models.GolsPartidaModel;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {IMappersDefault.class})
public interface IGolsPartidaMapper {

  // Source --> é o objeto de origem, de onde vêm os dados
  // Target --> é o objeto de destino, para onde os dados vão

  // DTO -> Model
  GolsPartidaModel toModel(GolsPartidaDTO dto);

  // Model --> DTO
  GolsPartidaDTO toDTO(GolsPartidaModel model);
}
