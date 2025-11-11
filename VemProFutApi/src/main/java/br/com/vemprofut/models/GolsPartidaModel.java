package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "gols_partida")
@Getter
@Setter

public class GolsPartidaModel {

    public GolsPartidaModel(PeladeiroModel peladeiro, PartidasModel partida){
        this.peladeiro = peladeiro;
        this.partida = partida;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "fk_peladeiro")
    private PeladeiroModel peladeiro;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private PartidasModel partida;
}
