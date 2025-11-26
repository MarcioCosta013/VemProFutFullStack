package br.com.vemprofut.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "editores_fut")
@Getter
@Setter
@NoArgsConstructor
public class EditorModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_editor")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "fk_peladeiro", nullable = false)
  private PeladeiroModel peladeiro;

  @ManyToOne
  @JoinColumn(name = "fk_fut", nullable = false)
  private FutModel fut;
}
