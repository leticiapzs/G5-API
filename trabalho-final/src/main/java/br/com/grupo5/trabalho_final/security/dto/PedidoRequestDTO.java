package br.com.grupo5.trabalho_final.security.dto;

public class PedidoRequestDTO {

	private Integer idProduto;

	private Integer idCliente;
	
	private Integer quantidade;

	public PedidoRequestDTO() {
	}

	public PedidoRequestDTO(Integer idProduto, Integer idCliente, Integer quantidade) {
		this.idProduto = idProduto;
		this.idCliente = idCliente;
		this.quantidade = quantidade;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}



}
