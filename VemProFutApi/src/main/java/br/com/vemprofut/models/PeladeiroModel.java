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
    private Long id_peladeiro;

    @Column(nullable = false, length = 50)
    private String nome_peladeiro;

    @Column(nullable = false, length = 30)
    private String apelido_peladeiro;

    @Column(nullable = false, length = 100)
    private String descricao_peladeiro;

    @Column(nullable = false, length = 10)
    private String pe_dominante;

    @Column(nullable = false, length = 15)
    private String whatsapp_peladeiro;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "fk_historico_peladeiro")
    private HistoricoPeladeiroModel historicoPeladeiroModel = new HistoricoPeladeiroModel();

    @ManyToMany(mappedBy = "cartoesPeladeiro")
    private CartoesModel cartoesModelPeladeiro;

    @ManyToMany
    @JoinTable(
            name = "esta_peladeiro_partida",
            joinColumns = @JoinColumn(name = "fk_peladeiro"),
            inverseJoinColumns = @JoinColumn(name = "fk_partida")
    )
    private List<PartidasModel> partidas;
}
