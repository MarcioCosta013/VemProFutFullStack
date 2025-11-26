package br.com.vemprofut.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "fut")
@Getter
@Setter
public class FutModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_fut")
  private Long id;

  @Column(name = "nome_fut", nullable = false, length = 50, unique = true)
  private String nome;

  @Column(name = "jogadores_por_time_fut", nullable = false)
  private Integer jogadoresPorTime;

  @Column(name = "tempo_max_partida_fut", nullable = false)
  private Integer tempoMaxPartida;

  @Column(name = "max_gols_vitoria_fut", nullable = false)
  private Integer maxGolsVitoria;

  @Column(name = "foto_url", length = 255)
  private String foto_url;

  @ToString.Exclude
  @OneToOne
  @JoinColumn(name = "fk_historico_fut")
  private HistoricoFutModel historicoFutId;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "fk_peladeiro")
  private PeladeiroModel administradorPeladeiro;

  @OneToMany(mappedBy = "fut", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EditorModel> editores = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "participa_peladeiro_fut",
      joinColumns = @JoinColumn(name = "fk_fut"),
      inverseJoinColumns = @JoinColumn(name = "fk_peladeiro"))
  private List<PeladeiroModel> peladeiros = new ArrayList<>();

  @OneToMany(mappedBy = "fut")
  private List<CartoesModel> cartoes = new ArrayList<>();

  @OneToMany(mappedBy = "fut")
  private List<BanimentoModel> banidos;

  public void addPeladeiroFut(PeladeiroModel peladeiroModel) {
    this.getPeladeiros().add(peladeiroModel);
    peladeiroModel.getFuts().add(this);
  }

  // Para adicionar nas duas entidades simultaneamente...
  public void addBanimento(BanimentoModel banimentoModel) {
    this.getBanidos().add(banimentoModel);
    banimentoModel.setFut(this);
  }
}
