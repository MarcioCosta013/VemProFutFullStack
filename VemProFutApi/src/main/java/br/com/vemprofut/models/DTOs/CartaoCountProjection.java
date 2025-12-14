package br.com.vemprofut.models.DTOs;

import br.com.vemprofut.models.enuns.TipoCartao;

// Essa interface está em DTOs porque ela representa um objeto de transferência de dados.
public interface CartaoCountProjection {
  TipoCartao getTipo();

  Long getQuantidade();

  /*
  - Usando o Spring Data Projection (interface) em vez de Object[] -
  O nome dos métodos (getTipo, getQuantidade) precisa bater com os aliases da query (SELECT c.tipo AS tipo, COUNT(c)
  AS quantidade). O Spring Data automaticamente cria a instância dessa interface preenchida com os resultados da consulta.
  */
}
