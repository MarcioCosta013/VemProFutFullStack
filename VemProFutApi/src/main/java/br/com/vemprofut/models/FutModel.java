package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "fut")
@Getter
@Setter
public class FutModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @Column(nullable = false)
    private Integer jogadoresPorTime;

    @Column(nullable = false)
    private Integer tempoMaxPartida;

    @Column(nullable = false)
    private Integer maxGolsVitoria;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "fk_historico_fut")
    private HistoricoFutModel historicoFutId;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "fk_peladeiro_adm")
    private PeladeiroModel administradorPeladeiro;

    @OneToMany(mappedBy = "fut", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EditorModel> editores = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "participa_peladeiro_fut",
            joinColumns = @JoinColumn(name = "fk_fut"),
            inverseJoinColumns = @JoinColumn(name = "fk_peladeiro")
    )
    private List<PeladeiroModel> peladeiros= new ArrayList<>();

    @OneToMany(mappedBy = "fut")
    private List<CartoesModel> cartoes = new ArrayList<>();
}
