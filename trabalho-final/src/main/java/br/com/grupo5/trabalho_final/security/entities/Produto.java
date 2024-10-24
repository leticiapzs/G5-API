package br.com.grupo5.trabalho_final.security.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

  @NotNull
  @Column(name = "prod_int_valor")
  private Double valor;

  @NotNull
  @Column(name = "prod_int_estoque")
  private Integer estoque;

  @ManyToOne
  @JoinColumn(name = "cat_fk_prod")
  private Categoria fkCategoria;

  @OneToOne
  @JoinColumn(name = "loj_fk_prod")
  private Loja fkLoja;

  @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
  private Set<PedidoProduto> pedidoProdutos;

  public Produto() {
  }

  public Produto(Integer id, String nome, String descricao, Double valor, Integer estoque, Categoria fkCategoria) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.valor = valor;
    this.estoque = estoque;
    this.fkCategoria = fkCategoria;
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

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Integer getEstoque() {
    return estoque;
  }

  public void setEstoque(Integer estoque) {
    this.estoque = estoque;
  }

  public Categoria getCategoria() {
    return fkCategoria;
  }

  public void setCategoria(Categoria fkCategoria) {
    this.fkCategoria = fkCategoria;
  }

  public Loja getLoja() {
    return fkLoja;
  }

  public void setLoja(Loja fkLoja) {
    this.fkLoja = fkLoja;
  }

  @Override
  public String toString() {
    return "Produto [descricao=" + descricao + ", estoque=" + estoque + ", id=" + id + ", nome=" + nome + ", valor="
        + valor + ", categoria=" + fkCategoria + "]";
  }
}