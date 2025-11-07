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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private PartidasModel partidaId;

    @ManyToOne
    @JoinColumn(name = "peladeiro_id")
    private PeladeiroModel peladeiroId;

    @ManyToOne
    @JoinColumn(name = "fut_id")
    private FutModel futId;

    @Enumerated(EnumType.STRING) // salva o nome do ENUM (AZUL, AMARELO, VERMELHO)
    @Column(name = "tipo_cartao", nullable = false)
    private TipoCartao tipoCartao;

}
