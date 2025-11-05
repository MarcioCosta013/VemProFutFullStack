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
    private Long idEditor;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "peladeiro_id", nullable = false)
    private PeladeiroModel peladeiroModel = new PeladeiroModel();

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "fut_id", nullable = false)
    private FutModel futModel = new FutModel();
}
