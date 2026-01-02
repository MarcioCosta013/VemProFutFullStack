package br.com.vemprofut.controllers.request;

import br.com.vemprofut.models.enuns.PeDominante;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SavePeladeiroRequestDTO(
    @NotBlank String nome,
    @Email String email,
    @NotNull String apelido,
    @NotBlank String descricao,
    @NotNull PeDominante peDominante,
    @NotBlank String whatsapp) {}
