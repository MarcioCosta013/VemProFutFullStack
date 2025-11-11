package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
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
    private List<PartidasModel> partidas = new ArrayList<>();

    @OneToMany(mappedBy = "peladeiroIdCartoes")
    private List<CartoesModel> cartoes = new ArrayList<>();

    @ManyToMany(mappedBy = "peladeiros")
    private List<FutModel> futs = new ArrayList<>();

    @OneToMany(mappedBy = "administradorPeladeiro")
    private List<FutModel> administra = new ArrayList<>();

    @OneToMany(mappedBy = "peladeiroIdEditor")
    private List<EditorModel> editores = new ArrayList<>();

    @OneToMany(mappedBy = "peladeiro")
    private List<GolsPartidaModel> golsPeladeiro = new ArrayList<>();
}
