package br.com.grupo5.trabalho_final.security.entities;

import br.com.grupo5.trabalho_final.security.embeddable.PedidoProdutoId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_produto")
public class PedidoProduto {

	@EmbeddedId
	private PedidoProdutoId id;

	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("produtoId")
	@JoinColumn(name = "produto_id", insertable = true, updatable = false)
	private Produto produto;

	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("pedidoId")
	@JoinColumn(name = "pedido_id", insertable = true, updatable = false)
	private Pedido pedido;

	private Integer quantidade;

	public PedidoProduto() {
	}

	public PedidoProduto(Pedido pedido, Produto produto, Integer quantidade) {
		this.id = new PedidoProdutoId(pedido.getId(), produto.getId());
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
}

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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "PedidoProduto [id=" + id + ", produto=" + produto + ", pedido=" + pedido + ", quantidade=" + quantidade
				+ "]";
	}

}