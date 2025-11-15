package br.com.vemprofut.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "peladeiro")
@Getter
@Setter
public class PeladeiroModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_peladeiro")
  private Long id;

  @Column(name = "nome_peladeiro", nullable = false, length = 50)
  private String nome;

  @Column(name = "email", nullable = false, length = 80, unique = true)
  private String email;

  @Column(name = "apelido_peladeiro", nullable = false, length = 30)
  private String apelido;

  @Column(name = "descricao_peladeiro", nullable = false, length = 100)
  private String descricao;

  @Column(name = "whatsapp_peladeiro", nullable = false, length = 15)
  private String whatsapp;

  @Column(name = "pe_dominante_peladeiro", nullable = false, length = 10)
  private String peDominante;

  @ToString.Exclude
  @OneToOne
  @JoinColumn(name = " fk_historico_peladeiro")
  private HistoricoPeladeiroModel historicoPeladeiro;

  @ManyToMany
  @JoinTable(
      name = "esta_peladeiro_partidas",
      joinColumns = @JoinColumn(name = "fk_peladeiro"),
      inverseJoinColumns = @JoinColumn(name = "fk_partida"))
  private List<PartidasModel> partidas = new ArrayList<>();

  @OneToMany(mappedBy = "peladeiro")
  private List<CartoesModel> cartoes = new ArrayList<>();

  @ManyToMany(mappedBy = "peladeiros")
  private List<FutModel> futs = new ArrayList<>();

  @OneToMany(mappedBy = "administradorPeladeiro")
  private List<FutModel> administra = new ArrayList<>();

  @OneToMany(mappedBy = "peladeiro")
  private List<EditorModel> editores = new ArrayList<>();

  @OneToMany(mappedBy = "peladeiro")
  private List<GolsPartidaModel> golsPeladeiro = new ArrayList<>();
}
