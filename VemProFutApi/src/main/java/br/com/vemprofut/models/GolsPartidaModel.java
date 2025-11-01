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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_gols_partida;

    @ToString.Exclude
    @JoinColumn(name = "fk_peladeiro")
    private PeladeiroModel peladeiroModel = new PeladeiroModel();

    @ManyToMany(mappedBy = "tem_gols_partida")
    private PartidasModel partida = new PartidasModel();
}
