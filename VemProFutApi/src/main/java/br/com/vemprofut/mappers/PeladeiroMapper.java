package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.PeladeiroModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeladeiroMapper {

    // DTO --> Model
    @Mapping(target = "idPeladeiro", source = "id")
    @Mapping(target = "nomePeladeiro", source = "nome")
    @Mapping(target = "emailPeladeiro", source = "email")
    @Mapping(target = "apelidoPeladeiro", source = "apelido")
    @Mapping(target = "descricaoPeladeiro", source = "descricao")
    @Mapping(target = "peDominante", source = "peDominante")
    @Mapping(target = "whatsappPeladeiro", source = "whatsapp")
    @Mapping(target = "cartoes", ignore = true)
    @Mapping(target = "historicoPeladeiroModel", ignore = true) // Preenche manualmente no service
    @Mapping(target = "partidas", ignore = true)
    @Mapping(target = "cartoesModelPeladeiro", ignore = true)
    @Mapping(target = "participaFut", ignore = true)
    PeladeiroModel toModel(PeladeiroDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "idPeladeiro")
    @Mapping(target = "nome", source = "nomePeladeiro")
    @Mapping(target = "email", source = "emailPeladeiro")
    @Mapping(target = "apelido", source = "apelidoPeladeiro")
    @Mapping(target = "descricao", source = "descricaoPeladeiro")
    @Mapping(target = "peDominante", source = "peDominante")
    @Mapping(target = "whatsapp", source = "whatsappPeladeiro")
    @Mapping(target = "cartoes", expression = "java(entity.getCartoesModel() != null ? entity.getCartoesModel().stream().map(c -> c.getIdCartoes()).toList() : null)")
    @Mapping(target = "historicoPeladeiro", expression = "java(entity.getHistoricoPeladeiroModel() != null ? entity.getHistoricoPeladeiroModel().getIdHistorico() : null)")
    @Mapping(target = "partidasIDs", expression = "java(entity.getPartidas() != null ? entity.getPartidas().stream().map(p -> p.getIdPartida()).toList() : null)")
    @Mapping(target = "futsIDs", expression = "java(entity.getParticipaFut() != null ? entity.getParticipaFut().stream().map(f -> f.getIdFut()).toList() : null)")
    PeladeiroDTO toDTO(PeladeiroModel model);
}
