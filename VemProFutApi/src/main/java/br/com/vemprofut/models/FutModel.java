package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table( name = "fut")
@Getter
@Setter
public class FutModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fut;

    @Column(nullable = false, length = 50)
    private String nome_fut;

    @Column(nullable = false)
    private Integer jogadores_por_time;

    @Column(nullable = false)
    private Integer tempo_max_partida;

    @Column(nullable = false)
    private Integer max_gols_vitoria;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "fk_historico_fut")
    private HistoricoFutModel historicoFutModel = new HistoricoFutModel();

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "fk_peladeiro_adm")
    private PeladeiroModel administradorPeladeiro = new PeladeiroModel();

    @ManyToMany
    @JoinTable(
            name = "participa_peladeiro_fut",
            joinColumns = @JoinColumn(name = "fk_fut"),
            inverseJoinColumns = @JoinColumn(name = "fk_peladeiro")
    )
    private List<PeladeiroModel> peladeiros;
}
