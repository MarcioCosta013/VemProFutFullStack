package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "partidas")
@Getter
@Setter
public class PartidasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_partida;

    @Column(nullable = false)
    private Boolean jogadores_reservas;

    @ToString.Exclude
    @JoinColumn(name = "fk_fut")
    private FutModel futModel = new FutModel();

    @OneToMany(mappedBy = "partida")
    private List<CartoesModel> cartoes;

    @ManyToMany
    @JoinTable(
            name = "tem_gols_partida",
            joinColumns = @JoinColumn (name = "fk_Partidas"),
            inverseJoinColumns = @JoinColumn (name = "fk_gols_partida")
    )
    private List<GolsPartidaModel> golsPartida;

    @ManyToMany(mappedBy = "esta_peladeiro_partida")
    private List<PeladeiroModel> peladeiros;
}
