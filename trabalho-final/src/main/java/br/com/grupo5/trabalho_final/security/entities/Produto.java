package br.com.grupo5.trabalho_final.security.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import java.util.Set;

@Entity
@Table(name = "produto")
public class Produto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Size(max = 20)
  private String nome;

  @NotBlank
  @Size(max = 50)
  @Email
  private String descricao;

  @NotBlank
  @Size(max = 120)
  private String estoque;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "pedido_id"))
  private Set<Pedido> pedidos;

  public Produto() {
  }

  public Produto(Integer id, String nome, String descricao, String estoque) {
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

  public String getEstoque() {
    return estoque;
  }

  public void setEstoque(String estoque) {
    this.estoque = estoque;
  }

  @Override
  public String toString() {
    return "Produto [descricao=" + descricao + ", estoque=" + estoque + ", id=" + id + ", nome=" + nome + "]";
  }
}
