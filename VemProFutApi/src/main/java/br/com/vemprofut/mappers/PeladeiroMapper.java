package br.com.vemprofut.mappers;

import br.com.vemprofut.models.DTOs.PeladeiroDTO;
import br.com.vemprofut.models.PeladeiroModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeladeiroMapper {

    // DTO --> Model
    @Mapping(target = "id_peladeiro", source = "id")
    @Mapping(target = "nome_peladeiro", source = "nome")
    @Mapping(target = "email_peladeiro", source = "email")
    @Mapping(target = "apelido_peladeiro", source = "apelido")
    @Mapping(target = "descricao_peladeiro", source = "descricao")
    @Mapping(target = "pe_dominante", source = "peDominante")
    @Mapping(target = "whatsapp_peladeiro", source = "whatsapp")
    @Mapping(target = "cartoes", ignore = true)
    @Mapping(target = "historicoPeladeiroModel", ignore = true) // Preenche manualmente no service
    @Mapping(target = "partidas", ignore = true)
    @Mapping(target = "cartoesModelPeladeiro", ignore = true)
    @Mapping(target = "participaFut", ignore = true)
    PeladeiroModel toModel(PeladeiroDTO dto);

    //Model --> DTO
    @Mapping(target = "id", source = "id_peladeiro")
    @Mapping(target = "nome", source = "nome_peladeiro")
    @Mapping(target = "email", source = "email_peladeiro")
    @Mapping(target = "apelido", source = "apelido_peladeiro")
    @Mapping(target = "descricao", source = "descricao_peladeiro")
    @Mapping(target = "peDominante", source = "pe_dominante")
    @Mapping(target = "whatsapp", source = "whatsapp_peladeiro")
    @Mapping(target = "cartoes", expression = "java(entity.getCartoesModel() != null ? entity.getCartoesModel().stream().map(c -> c.getId_cartoes()).toList() : null)")
    @Mapping(target = "historicoPeladeiro", expression = "java(entity.getHistoricoPeladeiroModel() != null ? entity.getHistoricoPeladeiroModel().getId_historico() : null)")
    @Mapping(target = "partidasIDs", expression = "java(entity.getPartidas() != null ? entity.getPartidas().stream().map(p -> p.getId_partida()).toList() : null)")
    @Mapping(target = "futsIDs", expression = "java(entity.getParticipaFut() != null ? entity.getParticipaFut().stream().map(f -> f.getId_fut()).toList() : null)")
    PeladeiroDTO toDTO(PeladeiroModel model);
}
