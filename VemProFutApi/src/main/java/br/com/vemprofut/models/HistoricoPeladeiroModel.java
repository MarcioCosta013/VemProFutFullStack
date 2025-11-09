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
    private Long id;

    @Column(nullable = false)
    private Integer golsDoPeladeiro = 0;

    @Column(nullable = false)
    private Double notaPeladeiro = 0.0;

    @Column(nullable = false)
    private Integer partidasJogadas = 0;

    @Column(nullable = false)
    private Integer partidasGanhas = 0;


}
