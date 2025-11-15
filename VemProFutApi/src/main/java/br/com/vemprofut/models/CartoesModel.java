package br.com.vemprofut.models;

import br.com.vemprofut.models.enuns.TipoCartao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cartoes_peladeiro")
@Getter
@Setter
public class CartoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartoes_peladeiro")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_partida")
    private PartidasModel partida;

    @ManyToOne
    @JoinColumn(name = "fk_peladeiro")
    private PeladeiroModel peladeiro;

    @ManyToOne
    @JoinColumn(name = "fk_fut")
    private FutModel fut;

    @Enumerated(EnumType.STRING) // salva o nome do ENUM (AZUL, AMARELO, VERMELHO)
    @Column(name = "tipoCartao", nullable = false)
    private TipoCartao tipoCartao;

}
