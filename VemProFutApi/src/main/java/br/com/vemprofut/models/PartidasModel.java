package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partidas")
@Getter
@Setter
@NoArgsConstructor
public class PartidasModel {

    public PartidasModel(Boolean reservas, FutModel futId){

        this.reservas = reservas;
        this.futId = futId;
        this.cartoes = new ArrayList<>();
        this.golsPartida = new ArrayList<>();
        this.peladeiros = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean reservas;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "fk_fut")
    private FutModel futId;

    @OneToMany(mappedBy = "partidaId")
    private List<CartoesModel> cartoes = new ArrayList<>();

    @OneToMany(mappedBy = "partida")
    private List<GolsPartidaModel> golsPartida = new ArrayList<>();

    @ManyToMany(mappedBy = "partidas")
    private List<PeladeiroModel> peladeiros = new ArrayList<>();
}
