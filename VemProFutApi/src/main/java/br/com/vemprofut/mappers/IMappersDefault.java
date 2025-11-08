package br.com.vemprofut.mappers;

import br.com.vemprofut.models.*;
import br.com.vemprofut.services.implementacao.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMappersDefault {

    // Converte List<CartoesModel> -> List<Long>
    default List<Long> mapIdsLongCartoes(List<CartoesModel> value) {
        return value == null ? null : value.stream().map(CartoesModel::getId).toList();
    }

    // Converte List<PeladeiroModel> -> List<Long>
    default List<Long> mapIdsLongPeladeiro(List<PeladeiroModel> value) {
        return value == null ? null : value.stream().map(PeladeiroModel::getId).toList();
    }

    // Converte List<Long> -> List<CartoesModel>
    default List<CartoesModel> mapIdsCartoes(List<Long> ids) {
        return ids == null ? null : ids.stream().map(id -> {
            CartoesModel c = new CartoesModel();
            c.setId(id);
            return c;
        }).toList();
    }

    //Converte HistoricoPeladeiroModel -> Long (pegando o id)
    default  Long mapIdHistoricoPeladeiro(HistoricoPeladeiroModel historico){
        return historico.getId();
    }

    //Converte PartidasModel -> Long (pegando o id)
    default  Long mapIdPartidas(PartidasModel partida){
        return partida.getId();
    }

    //Converte HistoricoFutModel -> Long (pegando o id)
    default  Long mapIdHistoricoPartidas(HistoricoFutModel historico){
        return historico.getId();
    }

    //Converte PartidasModel -> Long (pegando o id)
    default  Long mapIdPartidas(PeladeiroModel peladeiro){
        return peladeiro.getId();
    }

    //Converte FutModel -> Long (pegando o id)
    default  Long mapIdFut(FutModel fut){
        return fut.getId();
    }

    //Converte ID tipo Long para HistoricoPeladeiroModel
    default  HistoricoPeladeiroModel mapHistoricoPeladeiro(Long id){
        HistoricoPeladeiroService service = new HistoricoPeladeiroService();
        return service.findByIdModel(id);
    }

    //Converte ID tipo Long para HistoricoFutModel
    default HistoricoFutModel mapHistoricoFut(Long id){
        HistoricoFutService service = new HistoricoFutService();
        return service.findByIdModel(id);
    }

    //Converte ID tipo Long para PeladeiroModel
    default PeladeiroModel mapPeladeiro(Long id){
        PeladeiroService service = new PeladeiroService();
        return service.findByIdModel(id);
    }

    //Converte ID tipo Long para FutModel
    default FutModel mapFut(Long id){
        FutService service = new FutService();
        return service.findByIdModel(id);
    }

    //Converte ID tipo Long para PartidasModel
    default PartidasModel mapPartidas(Long id){
        PartidasService service = new PartidasService();
        return service.findByIdModel(id);
    }
}
