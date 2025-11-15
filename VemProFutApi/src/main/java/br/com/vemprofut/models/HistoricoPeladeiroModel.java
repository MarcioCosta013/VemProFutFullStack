package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "historico_peladeiro")
@Getter
@Setter
public class HistoricoPeladeiroModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_historico_peladeiro")
  private Long id;

  @Column(name = "gols_historico_peladeiro", nullable = false)
  private Integer golsDoPeladeiro = 0;

  @Column(name = "nota_historico_peladeiro", nullable = false)
  private Double notaPeladeiro = 0.0;

  @Column(name = "partidas_jogadas_peladeiro", nullable = false)
  private Integer partidasJogadas = 0;

  @Column(name = "partidas_ganhas_peladeiro", nullable = false)
  private Integer partidasGanhas = 0;
}
