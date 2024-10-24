package br.com.grupo5.trabalho_final.security.dto;

import br.com.grupo5.trabalho_final.security.entities.Endereco;

public class EnderecoResponseDTO {

	private String cep;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;	
	private String localidade;
	private String uf;
	private String estado;
	
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Endereco toEndereco() {
        return new Endereco(this.cep, this.logradouro, this.numero, this.complemento, this.bairro, this.localidade, this.uf, this.estado);
    }
	
	public EnderecoResponseDTO (Endereco endereco) {
		this.cep = endereco.getCep();
		this.bairro = endereco.getBairro();
		this.complemento = endereco.getComplemento();
		this.estado = endereco.getEstado();
		this.localidade = endereco.getLocalidade();
		this.logradouro = endereco.getLogradouro();
		this.uf = endereco.getUf();
		this.numero = endereco.getNumero();
	}
	
	public EnderecoResponseDTO () {
	}

	public EnderecoResponseDTO(String cep, String logradouro, Integer numero, String complemento, String bairro,
			String localidade, String uf, String estado) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.estado = estado;
	}
	
}

