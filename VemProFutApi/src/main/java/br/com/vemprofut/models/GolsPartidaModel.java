package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.management.ConstructorParameters;

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
    private Long idGolsPartida;

    @ToString.Exclude
    @JoinColumn(name = "fk_peladeiro")
    private PeladeiroModel peladeiro = new PeladeiroModel();

    @ManyToMany(mappedBy = "tem_gols_partida")
    private PartidasModel partida = new PartidasModel();
}
