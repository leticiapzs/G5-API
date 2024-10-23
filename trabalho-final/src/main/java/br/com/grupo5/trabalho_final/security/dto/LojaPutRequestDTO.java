package br.com.grupo5.trabalho_final.security.dto;

public class LojaPutRequestDTO {
	
	private String cnpj;
	private String nomeFantasia;
	
	public LojaPutRequestDTO() {
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

}
