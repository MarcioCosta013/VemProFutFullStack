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
    private Long idHistoricoPeladeiro;

    @Column(nullable = false)
    private Integer golsHistoricoPeladeiro;

    @Column(nullable = false)
    private Double notaPeladeiro;

    @Column(nullable = false)
    private Integer partidasJogadasHistorico;

    @Column(nullable = false)
    private Integer partidasGanhasHistorico;


}
