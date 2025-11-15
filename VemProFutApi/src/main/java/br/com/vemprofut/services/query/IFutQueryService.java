package br.com.vemprofut.services.query;

import br.com.vemprofut.models.DTOs.FutDTO;
import br.com.vemprofut.models.FutModel;

public interface IFutQueryService {

    void verifyFutExist(Long dto);

    FutModel verifyFutExistRetorn(Long fut);

    void verifyNomeFutExist(String nome);

    FutModel verifyNomeFutExistRetorn(String nome);
}
