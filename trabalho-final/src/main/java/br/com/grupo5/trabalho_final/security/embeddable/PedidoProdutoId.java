package br.com.grupo5.trabalho_final.security.embeddable;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PedidoProdutoId implements Serializable {
   
	private static final long serialVersionUID = 1L;

@Column(name = "produto_id")
  private Integer produtoId;

  @Column(name = "pedido_id")
  private Integer pedidoId;

  // Default constructor
  public PedidoProdutoId() {
  }

  // Parameterized constructor
  public PedidoProdutoId(Integer produtoId, Integer pedidoId) {
    this.produtoId = produtoId;
    this.pedidoId = pedidoId;
  }

  // Getters and setters
  public Integer getProdutoId() {
    return produtoId;
  }

  public void setProdutoId(Integer produtoId) {
    this.produtoId = produtoId;
  }

  public Integer getPedidoId() {
    return pedidoId;
  }

  public void setPedidoId(Integer pedidoId) {
    this.pedidoId = pedidoId;
  }

  // Override equals and hashCode
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    PedidoProdutoId that = (PedidoProdutoId) o;
    return Objects.equals(produtoId, that.produtoId) && Objects.equals(pedidoId, that.pedidoId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(produtoId, pedidoId);
  }
}