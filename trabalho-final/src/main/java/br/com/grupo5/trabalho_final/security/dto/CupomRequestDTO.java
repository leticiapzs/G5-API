package br.com.grupo5.trabalho_final.security.dto;

public class CupomRequestDTO {

	 private Integer desconto;
	 
	 private String codigo;
	 
	 private boolean boolean_ativo;
	 
	 public CupomRequestDTO() {

	  }
	 
	 public CupomRequestDTO(Integer desconto, String codigo, boolean boolean_ativo) {
		 
		 this.desconto = desconto;
		 
		 this.codigo = codigo;
		 
		 this.boolean_ativo = boolean_ativo;
}

	public Integer getDesconto() {
		return desconto;
	}

	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isBoolean_ativo() {
		return boolean_ativo;
	}

	public void setBoolean_ativo(boolean boolean_ativo) {
		this.boolean_ativo = boolean_ativo;
	}
	 
	 
	 
}