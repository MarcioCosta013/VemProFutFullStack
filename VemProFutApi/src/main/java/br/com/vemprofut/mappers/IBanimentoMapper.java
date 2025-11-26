package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.response.BanimentoDetailsResponseDTO;
import br.com.vemprofut.controllers.response.SaveBanimentoResponseDTO;
import br.com.vemprofut.models.BanimentoModel;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {IMappersDefault.class})
public interface IBanimentoMapper {

  SaveBanimentoResponseDTO toSaveResponse(BanimentoModel model);

  BanimentoDetailsResponseDTO toDetails(BanimentoModel model);

  List<BanimentoDetailsResponseDTO> toDetailsList(List<BanimentoModel> listModel);
}
