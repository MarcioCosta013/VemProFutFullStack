package br.com.vemprofut.services.query;

import br.com.vemprofut.models.PeladeiroModel;

public interface IPeladeiroQueryService {

    void verifyEmail(final String email);

    PeladeiroModel verifyPeladeiroExist(final Long id);

}
