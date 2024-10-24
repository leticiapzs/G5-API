package br.com.grupo5.trabalho_final.security.dto;

public class LojaResponseDTO {

	private String cnpj;

	private String nomeFantasia;

	private EnderecoResponseDTO fkEndereco;

	private String username;
	
	public LojaResponseDTO() {
	}

	public LojaResponseDTO(String cnpj, String nomeFantasia, EnderecoResponseDTO fkEndereco, String username) {
		super();
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.fkEndereco = fkEndereco;
		this.username = username;
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

	public EnderecoResponseDTO getFkEndereco() {
		return fkEndereco;
	}

	public void setFkEndereco(EnderecoResponseDTO fkEndereco) {
		this.fkEndereco = fkEndereco;
	}

	public String getUsername() {
		return username;
	}

	public void setFkUser(String username) {
		this.username = username;
	}
	
}
