package br.com.grupo5.trabalho_final.security.dto;

import br.com.grupo5.trabalho_final.security.entities.Cliente;
import br.com.grupo5.trabalho_final.security.entities.Produto;

public class PedidoRequestDTO {

	private Produto produto;

	private Cliente cliente;
	
	private Integer quantidade;
	
	public PedidoRequestDTO() {
	}

	public PedidoRequestDTO(Produto produto, Cliente cliente, Integer quantidade) {
		this.produto = produto;
		this.cliente = cliente;
		this.quantidade = quantidade;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}	

}
