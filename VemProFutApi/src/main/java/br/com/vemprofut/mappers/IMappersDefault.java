package br.com.vemprofut.mappers;

import br.com.vemprofut.controllers.response.CartoesResumoResponseDTO;
import br.com.vemprofut.models.*;
import br.com.vemprofut.services.implementacao.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMappersDefault {

    // ==========================CARTOES==========================

    // Converte List<CartoesModel> -> List<Long>
    default List<Long> mapIdsLongCartoes(List<CartoesModel> value) {
        return value == null ? null : value.stream().map(CartoesModel::getId).toList();
    }

    // Converte List<Long> -> List<CartoesModel>
    default List<CartoesModel> mapIdsCartoes(List<Long> ids) {
        return ids == null ? null : ids.stream().map(id -> {
            CartoesModel c = new CartoesModel();
            c.setId(id);
            return c;
        }).toList();
    }

    default CartoesResumoResponseDTO mapResumeCartoes (List<CartoesModel> value){
        CartoesService cartoesService = new CartoesService();
        long id = value.getFirst().getPeladeiroId().getId(); //Nessa lista de cartoes todos tem o mesmo id de peladeiro.
        return cartoesService.contarCartoesPeladeiro(id);
    }

    //========================EDITOR=====================

    //Converte EditorModel -> Long (pegando o id)
    default  Long mapIdGols(EditorModel editor){
        return editor.getId();
    }
    //Converte ID tipo Long para EditorModel
    default EditorModel mapEditor(Long id){
        EditorService service = new EditorService();
        return service.findByIdModel(id);
    }

    //======================FUT======================

    //Converte FutModel -> Long (pegando o id)
    default  Long mapIdFut(FutModel fut){
        return fut.getId();
    }
    //Converte ID tipo Long para FutModel
    default FutModel mapFut(Long id){
        FutService service = new FutService();
        return service.findByIdModel(id);
    }

    //===========================GOLS=================

    //Converte GolsPartidaModel -> Long (pegando o id)
    default  Long mapIdGols(GolsPartidaModel gols){
        return gols.getId();
    }
    //Converte ID tipo Long para GolsPartidaModel
    default GolsPartidaModel mapgols(Long id){
        GolsPartidaService service = new GolsPartidaService();
        return service.findByIdModel(id);
    }

    //===================HISTORICO FUT==================

    //Converte HistoricoFutModel -> Long (pegando o id)
    default  Long mapIdHistoricoPartidas(HistoricoFutModel historico){
        return historico.getId();
    }
    //Converte ID tipo Long para HistoricoFutModel
    default HistoricoFutModel mapHistoricoFut(Long id){
        HistoricoFutService service = new HistoricoFutService();
        return service.findByIdModel(id);
    }

    //=====================HISTORICO PELADEIRO================

    //Converte HistoricoPeladeiroModel -> Long (pegando o id)
    default  Long mapIdHistoricoPeladeiro(HistoricoPeladeiroModel historico){
        return historico.getId();
    }
    //Converte ID tipo Long para HistoricoPeladeiroModel
    default  HistoricoPeladeiroModel mapHistoricoPeladeiro(Long id){
        HistoricoPeladeiroService service = new HistoricoPeladeiroService();
        return service.findByIdModel(id);
    }

    //============================PARTIDAS===========================

    //Converte PartidasModel -> Long (pegando o id)
    default  Long mapIdPartidas(PartidasModel partida){
        return partida.getId();
    }
    //Converte ID tipo Long para PartidasModel
    default PartidasModel mapPartidas(Long id){
        PartidasService service = new PartidasService();
        return service.findByIdModel(id);
    }

    //=========================PELADEIRO=========================

    // Converte List<PeladeiroModel> -> List<Long>
    default List<Long> mapIdsLongPeladeiro(List<PeladeiroModel> value) {
        return value == null ? null : value.stream().map(PeladeiroModel::getId).toList();
    }
    //Converte PELADEIROModel -> Long (pegando o id)
    default  Long mapIdPartidas(PeladeiroModel peladeiro){
        return peladeiro.getId();
    }
    //Converte ID tipo Long para PeladeiroModel
    default PeladeiroModel mapPeladeiro(Long id){
        PeladeiroService service = new PeladeiroService();
        return service.findByIdModel(id);
    }




















}
