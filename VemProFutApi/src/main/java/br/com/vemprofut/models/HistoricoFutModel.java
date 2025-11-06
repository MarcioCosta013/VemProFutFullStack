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
    private Integer golsTotalFut = 0;

    @Column(nullable = false)
    private Integer totalPartidasJogadas = 0;

    @Column(nullable = false)
    private String timeMaisVitorias = "vazio";

}
