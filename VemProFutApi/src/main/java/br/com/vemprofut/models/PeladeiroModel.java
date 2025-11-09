package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "peladeiro")
@Getter
@Setter
public class PeladeiroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 80, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    private String apelido;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 10)
    private String peDominante;

    @Column(nullable = false, length = 15)
    private String whatsapp;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "fk_historico_peladeiro")
    private HistoricoPeladeiroModel historicoPeladeiro;
    @ManyToMany
    @JoinTable(
            name = "esta_peladeiro_partida",
            joinColumns = @JoinColumn(name = "fk_peladeiro"),
            inverseJoinColumns = @JoinColumn(name = "fk_partida")
    )
    private List<PartidasModel> partidas;

    @OneToMany(mappedBy = "peladeiro")
    private List<CartoesModel> cartoes;

    @ManyToMany(mappedBy = "participa_peladeiro_fut")
    private List<FutModel> futs;
}
