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
    private Long id_historico_fut;

    @Column(nullable = false)
    private Integer gols_total_fut;

    @Column(nullable = false)
    private Integer total_partidas_jogadas;

    @Column(nullable = false)
    private String time_mais_vitorias;

}
