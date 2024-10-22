package br.com.grupo5.trabalho_final.security.entities;

import jakarta.persistence.Entity;
import br.com.grupo5.trabalho_final.security.embeddable.PedidoProdutoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_produto")
public class PedidoProduto {
  @EmbeddedId
  private PedidoProdutoId id;

  @ManyToOne
  @JoinColumn(name = "produto_id", insertable = false, updatable = false)
  private Produto produto;

  @ManyToOne
  @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
  private Pedido pedido;

  private Integer estoque;

  // Getters and setters
  public PedidoProdutoId getId() {
    return id;
  }

  public void setId(PedidoProdutoId id) {
    this.id = id;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  public Integer getEstoque() {
    return estoque;
  }

  public void setEstoque(Integer estoque) {
    this.estoque = estoque;
  }
}