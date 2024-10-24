package br.com.grupo5.trabalho_final.security.dto;

import java.time.LocalDate;

public class ClienteRequestDTO {

	private String nomeCompleto;
	private String cpf;
	private LocalDate dataDeNascimento;
	private String username;
	private String email;
	private String password;
	private String complemento;
	private Integer numero;
	private String cep;

	public ClienteRequestDTO() {
	}

	public ClienteRequestDTO(String nomeCompleto, String cpf, LocalDate dataDeNascimento, String username, String email,
			String password, String complemento, Integer numero, String cep) {
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.username = username;
		this.email = email;
		this.password = password;
		this.complemento = complemento;
		this.numero = numero;
		this.cep = cep;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public EnderecoRequestDTO toEnderecoDTO() {
		return new EnderecoRequestDTO(this.cep, this.complemento, this.numero);

	}

	public SignupRequestDTO toSingupDTO() {
		return new SignupRequestDTO(this.username, this.password, this.email);
	}
}