package br.com.vemprofut.controllers.request;

import br.com.vemprofut.models.CartoesModel;
import br.com.vemprofut.models.DTOs.HistoricoPeladeiroDTO;
import br.com.vemprofut.models.FutModel;
import br.com.vemprofut.models.PartidasModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SavePeladeiroRequestDTO(
        @NotBlank String nome,
        @Email String email,
        @NotNull String apelido,
        @NotBlank String descricao,
        @NotNull String peDominante,
        @NotBlank String whatsapp,
        @NotBlank HistoricoPeladeiroDTO historicoPeladeiro,
        List<PartidasModel> partidas,
        List<CartoesModel> cartoes,
        List<FutModel> futs
        ) {}
