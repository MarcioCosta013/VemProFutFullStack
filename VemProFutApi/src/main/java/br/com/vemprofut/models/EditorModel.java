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
    private Long id_editor;

    @ToString.Exclude
    @ManyToOne
    private PeladeiroModel peladeiroModel = new PeladeiroModel();

    @ToString.Exclude
    @ManyToOne
    private FutModel futModel = new FutModel();
}
