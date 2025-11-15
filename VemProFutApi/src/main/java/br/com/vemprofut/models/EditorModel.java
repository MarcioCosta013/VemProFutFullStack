package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "editores_fut")
@Getter
@Setter
public class EditorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editor")
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "fk_peladeiro", nullable = false)
    private PeladeiroModel peladeiro;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "fk_fut", nullable = false)
    private FutModel fut;
}
