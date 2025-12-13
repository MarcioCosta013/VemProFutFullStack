package br.com.vemprofut.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "banimento")
@Getter
@Setter
@NoArgsConstructor
public class BanimentoModel {

  public BanimentoModel(
      String motivo,
      LocalDate dataBanimento,
      LocalDate dataFimBanimento,
      PeladeiroModel peladeiro,
      FutModel fut) {
    this.motivo = motivo;
    this.dataBanimento = dataBanimento;
    this.dataFimBanimento = dataFimBanimento;
    this.peladeiro = peladeiro;
    this.fut = fut;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_banimento")
  private Long id;

  @Column(name = "motivo_banimento")
  private String motivo;

  @Column(name = "data_banimento")
  private LocalDate dataBanimento;

  @Column(name = "data_fim_banimento")
  private LocalDate dataFimBanimento;

  @ManyToOne
  @JoinColumn(name = "fk_peladeiro", nullable = false)
  private PeladeiroModel peladeiro;

  @ManyToOne
  @JoinColumn(name = "fk_fut", nullable = false)
  private FutModel fut;

  // Para adicionar nas duas entidades simultaneamente...
  public void addFut(FutModel futModel) {
    this.setFut(futModel);
    futModel.getBanidos().add(this);
  }

  public void addPeladeiro(PeladeiroModel peladeiroModel) {
    this.setPeladeiro(peladeiroModel);
    peladeiroModel.getBanimentos().add(this);
  }
}
