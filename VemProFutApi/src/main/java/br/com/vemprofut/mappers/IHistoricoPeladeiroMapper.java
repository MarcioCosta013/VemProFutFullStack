package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {IMappersDefault.class})
public interface IHistoricoPeladeiroMapper {

  // DTO -> Model
  HistoricoPeladeiroModel toModel(HistoricoPeladeiroDTO dto);

  // Model --> DTO
  HistoricoPeladeiroDTO toDTO(HistoricoPeladeiroModel model);
}
