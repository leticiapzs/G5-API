package br.com.grupo5.trabalho_final.security.dto;

import br.com.grupo5.trabalho_final.security.entities.Cupom;

public class CupomResponseDTO {

	private Integer desconto;
	private Integer codigo;
	private boolean boolean_ativo;
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public boolean isBoolean_ativo() {
		return boolean_ativo;
	}
	public void setBoolean_ativo(boolean boolean_ativo) {
		this.boolean_ativo = boolean_ativo;
	}
	
	public Cupom toCupom() {
		return new Cupom(this.desconto, this.codigo, this.boolean_ativo);
	}

	
	
}
