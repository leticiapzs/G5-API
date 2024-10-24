package br.com.grupo5.trabalho_final.security.dto;

import java.util.Date;
import java.util.Set;

import br.com.grupo5.trabalho_final.security.entities.Cliente;
import br.com.grupo5.trabalho_final.security.entities.PedidoProduto;

public class PedidoRequestDTO {

	private Date date;

	private Set<PedidoProduto> pedidoProdutos;

	private Cliente cliente;
	
	private Integer quantidade;
	
	public PedidoRequestDTO() {
	}

	public PedidoRequestDTO(Date date, Set<PedidoProduto> pedidoProdutos, Cliente cliente, Integer quantidade) {
		this.date = date;
		this.pedidoProdutos = pedidoProdutos;
		this.cliente = cliente;
		this.quantidade = quantidade;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<PedidoProduto> getPedidoProdutos() {
		return pedidoProdutos;
	}

	public void setPedidoProdutos(Set<PedidoProduto> pedidoProdutos) {
		this.pedidoProdutos = pedidoProdutos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}	

}
