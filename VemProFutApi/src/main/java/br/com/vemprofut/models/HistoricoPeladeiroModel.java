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
    private Long id_historico_peladeiro;

    @Column(nullable = false)
    private Integer gols_historico_peladeiro;

    @Column(nullable = false)
    private Double nota_peladeiro;

    @Column(nullable = false)
    private Integer partidas_jogadas_historico;

    @Column(nullable = false)
    private Integer partidas_ganhas_historico;


}
