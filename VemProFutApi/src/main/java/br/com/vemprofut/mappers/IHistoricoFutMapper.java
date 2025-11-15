package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.response.SaveHistoricoFutResponseDTO;
import br.com.vemprofut.models.DTOs.HistoricoFutDTO;
import br.com.vemprofut.models.HistoricoFutModel;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {IMappersDefault.class})
public interface IHistoricoFutMapper {

  // DTO -> model
  HistoricoFutModel toModel(HistoricoFutDTO dto);

  // Model -> DTO
  HistoricoFutDTO toDTO(HistoricoFutModel model);

  SaveHistoricoFutResponseDTO toSaveResponse(HistoricoFutModel historico);
}
