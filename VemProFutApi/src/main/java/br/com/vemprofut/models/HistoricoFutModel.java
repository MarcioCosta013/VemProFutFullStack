package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "historico_fut")
@Getter
@Setter
public class HistoricoFutModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_historico_fut")
  private Long id;

  @Column(name = "gols_total_historico_fut", nullable = false)
  private Integer golsTotal = 0;

  @Column(name = "partidas_jogadas_historico_fut", nullable = false)
  private Integer totalPartidas = 0;

  @Column(name = "time_mais_vitorias_historico", nullable = false)
  private String timeMaisVitorias = "vazio";
}
