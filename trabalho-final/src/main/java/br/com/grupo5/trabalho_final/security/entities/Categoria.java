package br.com.grupo5.trabalho_final.security.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cat_cd_id")
  private Integer id;

  @Column(name = "cat_tx_nome")
  private String nome;

  @Column(name = "cat_tx_descricao")
  private String descricao;

  @OneToMany
  @JoinColumn(name = "cat_fk_prod")
  private List<Produto> fkProduto;

  public Categoria() {
  }

  public Categoria(Integer id, String nome, List<Produto> fkProduto) {
    this.id = id;
    this.nome = nome;
    this.fkProduto = fkProduto;
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

  public List<Produto> getFkProduto() {
    return fkProduto;
  }

  public void setFkProduto(List<Produto> fkProduto) {
    this.fkProduto = fkProduto;
  }

  @Override
  public String toString() {
    return "Categoria [id=" + id + ", nome=" + nome + ", fkProduto=" + fkProduto + "]";
  }

}
