package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "historico_fut")
@Getter
@Setter
public class HistoricoFutModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoricoFut;

    @Column(nullable = false)
    private Integer golsTotalFut;

    @Column(nullable = false)
    private Integer totalPartidasJogadas;

    @Column(nullable = false)
    private String timeMaisVitorias;

}
