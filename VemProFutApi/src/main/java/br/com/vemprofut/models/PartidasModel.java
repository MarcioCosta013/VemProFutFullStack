package br.com.vemprofut.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "partidas")
@Getter
@Setter
@NoArgsConstructor
public class PartidasModel {

  public PartidasModel(Boolean reservas, FutModel futId) {

    this.reservas = reservas;
    this.futId = futId;
    this.cartoes = new ArrayList<>();
    this.golsPartida = new ArrayList<>();
    this.peladeiros = new ArrayList<>();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_partida")
  private Long id;

  @Column(name = "reservas_partida", nullable = false)
  private Boolean reservas;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "fk_fut")
  private FutModel futId;

  @OneToMany(mappedBy = "partida")
  private List<CartoesModel> cartoes = new ArrayList<>();

  @OneToMany(mappedBy = "partida")
  private List<GolsPartidaModel> golsPartida = new ArrayList<>();

  @ManyToMany(mappedBy = "partidas")
  private List<PeladeiroModel> peladeiros = new ArrayList<>();

  /*
    Metodo Helper para manter as duas listas com tabelas intermediarias
    no banco de dados sincronizadas... e para a tabela intermediaria ser alimentada
    sempre o lado owner side (lado dono) deve adicionar...(quem tem o @JoinTable
    com o name="nome da tabela intermediaria"
   */
  public void addPeladeiro(PeladeiroModel peladeiroModel){
    this.peladeiros.add(peladeiroModel); //adicione na lista dessa classe um peladeiro
    peladeiroModel.getPartidas().add(this);// e lá em peladeiro, na lista de partidas adicione essa partida que vos fala.
  }
}
