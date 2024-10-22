package br.com.grupo5.trabalho_final.security.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

// import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.JoinColumn;

import java.util.Set;

@Entity
@Table(name = "produto")
public class Produto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "prod_cd_id")
  private Integer id;

  @NotBlank
  @Column(name = "prod_tx_nome")
  private String nome;

  @NotBlank
  @Column(name = "prod_tx_descricao")
  private String descricao;

  @NotBlank
  @Column(name = "prod_int_estoque")
  private Integer estoque;

  @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
  private Set<PedidoProduto> pedidoProdutos;

  // @ManyToMany(fetch = FetchType.LAZY)
  // @JoinTable(name = "pedido_produto", joinColumns = @JoinColumn(name =
  // "produto_id"), inverseJoinColumns = @JoinColumn(name = "pedido_id"))
  // private Set<Pedido> pedidos;

  public Produto() {
  }

  public Produto(Integer id, String nome, String descricao, Integer estoque) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.estoque = estoque;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Integer getEstoque() {
    return estoque;
  }

  public void setEstoque(Integer estoque) {
    this.estoque = estoque;
  }

  @Override
  public String toString() {
    return "Produto [descricao=" + descricao + ", estoque=" + estoque + ", id=" + id + ", nome=" + nome + "]";
  }
}
