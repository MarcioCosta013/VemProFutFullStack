package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cartoes_peladeiro")
@Getter
@Setter
public class CartoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cartoes;

    @Column(nullable = false)
    private Integer azuis_cartoes;

    @Column(nullable = false)
    private Integer amarelos_cartoes;

    @Column(nullable = false)
    private Integer vermelhos_cartoes;

    @ManyToMany
    @JoinTable(
            name = "cartoes_partidas",
            joinColumns = @JoinColumn(name = "fk_cartoes"),
            inverseJoinColumns = @JoinColumn(name = "fk_partida")
    )
    private List<PartidasModel> cartoesPartida;

    @ManyToMany
    @JoinTable(
        name = "cartoes_peladeiro",
        joinColumns = @JoinColumn(name = "fk_cartoes"),
        inverseJoinColumns = @JoinColumn(name = "fk_peladeiro")
    )
    private List<PeladeiroModel> cartoesPeladeiro;
}
