package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.response.CartoesResumoResponseDTO;
import br.com.vemprofut.controllers.response.PeladeiroResponseDTO;
import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.EditorModel;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.GolsPartidaModel;
import br.com.vemprofut.models.HistoricoFutModel;
import br.com.vemprofut.models.HistoricoPeladeiroModel;
import br.com.vemprofut.models.PartidasModel;
import br.com.vemprofut.models.PeladeiroModel;
import br.com.vemprofut.services.IEditorService;
import br.com.vemprofut.services.IFutService;
import br.com.vemprofut.services.IGolsPartidaService;
import br.com.vemprofut.services.IHistoricoFutService;
import br.com.vemprofut.services.IHistoricoPeladeiroService;
import br.com.vemprofut.services.IPartidasService;
import br.com.vemprofut.services.IPeladeiroService;
import br.com.vemprofut.services.implementacao.*;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMappersDefault {

  // ==========================CARTOES==========================

  // Converte List<CartoesModel> -> List<Long>
  default List<Long> mapIdsLongCartoes(List<CartoesModel> value) {
    return value == null ? null : value.stream().map(CartoesModel::getId).toList();
  }

  // Converte List<Long> -> List<CartoesModel>
  default List<CartoesModel> mapIdsCartoes(List<Long> ids) {
    return ids == null
        ? null
        : ids.stream()
            .map(
                id -> {
                  CartoesModel c = new CartoesModel();
                  c.setId(id);
                  return c;
                })
            .toList();
  }

  default CartoesResumoResponseDTO mapResumoCartoes(List<CartoesModel> cartoes) {
    CartoesResumoResponseDTO resultados = new CartoesResumoResponseDTO();

    int azul = 0, amarelo = 0, vermelho = 0;
    for (CartoesModel cartao : cartoes) {
      switch (cartao.getTipoCartao()) {
        case AZUL -> azul++;
        case AMARELO -> amarelo++;
        case VERMELHO -> vermelho++;
      }
    }

    resultados.setAzul(azul);
    resultados.setAmarelo(amarelo);
    resultados.setVermelho(vermelho);

    return resultados;
  }

  // ========================EDITOR=====================

  // Converte EditorModel -> Long (pegando o id)
  default Long mapIdGols(EditorModel editor) {
    return editor.getId();
  }

  // Converte ID tipo Long para EditorModel
  default EditorModel mapEditor(Long id) {
    IEditorService service = new EditorService();
    return service.findByIdModel(id);
  }

  // ======================FUT======================

  // Converte FutModel -> Long (pegando o id)
  default Long mapIdFut(FutModel fut) {
    return fut.getId();
  }

  // Converte ID tipo Long para FutModel
  default FutModel mapFut(Long id) {
    IFutService service = new FutService();
    return service.findByIdModel(id);
  }

  // ===========================GOLS=================

  // Converte GolsPartidaModel -> Long (pegando o id)
  default Long mapIdGols(GolsPartidaModel gols) {
    return gols.getId();
  }

  // Converte ID tipo Long para GolsPartidaModel
  default GolsPartidaModel mapgols(Long id) {
    IGolsPartidaService service = new GolsPartidaService();
    return service.findByIdModel(id);
  }

  // ===================HISTORICO FUT==================

  // Converte HistoricoFutModel -> Long (pegando o id)
  default Long mapIdHistoricoPartidas(HistoricoFutModel historico) {
    return historico.getId();
  }

  // Converte ID tipo Long para HistoricoFutModel
  default HistoricoFutModel mapHistoricoFut(Long id) {
    IHistoricoFutService service = new HistoricoFutService();
    return service.findByIdModel(id);
  }

  // =====================HISTORICO PELADEIRO================

  // Converte HistoricoPeladeiroModel -> Long (pegando o id)
  default Long mapIdHistoricoPeladeiro(HistoricoPeladeiroModel historico) {
    return historico.getId();
  }

  // Converte ID tipo Long para HistoricoPeladeiroModel
  default HistoricoPeladeiroModel mapHistoricoPeladeiro(Long id) {
    IHistoricoPeladeiroService service = new HistoricoPeladeiroService();
    return service.findByIdModel(id);
  }

  // ============================PARTIDAS===========================

  // Converte PartidasModel -> Long (pegando o id)
  default Long mapIdPartidas(PartidasModel partida) {
    return partida.getId();
  }

  // Converte ID tipo Long para PartidasModel
  default PartidasModel mapPartidas(Long id) {
    IPartidasService service = new PartidasService();
    return service.findByIdModel(id);
  }

  // =========================PELADEIRO=========================

  // Converte List<PeladeiroModel> -> List<Long>
  default List<Long> mapIdsLongPeladeiro(List<PeladeiroModel> value) {
    return value == null ? null : value.stream().map(PeladeiroModel::getId).toList();
  }

  // Converte PELADEIROModel -> Long (pegando o id)
  default Long mapIdPartidas(PeladeiroModel peladeiro) {
    return peladeiro.getId();
  }

  // Converte ID tipo Long para PeladeiroModel
  default PeladeiroModel mapPeladeiro(Long id) {
    IPeladeiroService service = new PeladeiroService();
    return service.findByIdModel(id);
  }

  default PeladeiroResponseDTO mapModelToResponse(PeladeiroModel model) {
    if (model == null) {
      return null;
    }
    Long idPeladeiro = model.getId();
    return new PeladeiroResponseDTO(idPeladeiro);
  }

  //  default PeladeiroNameIdResponseDTO toResponseNameId(EditorModel model) {
  //    if (model == null) {
  //      return null;
  //    }
  //    PeladeiroModel peladeiroModel = model.getPeladeiro();
  //    Long peladeiroId = peladeiroModel.getId();
  //    String nome = peladeiroModel.getNome();
  //
  //    return new PeladeiroNameIdResponseDTO(peladeiroId, nome);
  //  }
}
