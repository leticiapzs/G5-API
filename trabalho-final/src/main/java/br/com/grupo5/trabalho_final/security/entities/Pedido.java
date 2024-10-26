package br.com.grupo5.trabalho_final.security.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
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

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ped_cd_id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<PedidoProduto> pedidoProdutos = new HashSet<>();

	@Column(name = "ped_int_valortotal")
	private Double valorTotal;

	@Column(name = "ped_bool_ativo")
	private boolean ativo;

	@Column(name = "date")
	private Date date;

	public Pedido() {
	}

	public Pedido(Double valorTotal, Set<PedidoProduto> pedidoProdutos, Cliente cliente) {
		this.valorTotal = valorTotal;
		this.pedidoProdutos = pedidoProdutos;
		this.cliente = cliente;
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
		this.pedidoProdutos = new HashSet<>();
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Set<PedidoProduto> getPedidoProdutos() {
		return pedidoProdutos;
	}

	public void setPedidoProdutos(Set<PedidoProduto> pedidoProdutos) {
		this.pedidoProdutos = pedidoProdutos;
	}

	public void adicionarProduto(PedidoProduto produto) {
		this.pedidoProdutos.add(produto);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@PrePersist
	protected void onCreation() {
		this.ativo = true;
	}

	// @PrePersist
	// protected void onCreate() {
	// this.date = new Date();
	// }

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", date=" + date + "]";
	}

}
