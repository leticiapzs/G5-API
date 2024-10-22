package br.com.grupo5.trabalho_final.security.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "pedido")
public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ped_cd_id")
  private Integer id;

  @Column(name = "date")
  private Date date;

  public Pedido() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @PrePersist
  protected void onCreate() {
    this.date = new Date();
  }

  @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
  private Set<PedidoProduto> pedidoProdutos;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @Override
  public String toString() {
    return "Pedido [id=" + id + ", date=" + date + "]";
  }

}
