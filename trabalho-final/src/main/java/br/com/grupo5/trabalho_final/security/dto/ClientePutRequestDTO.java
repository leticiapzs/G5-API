package br.com.grupo5.trabalho_final.security.dto;

import java.time.LocalDate;

public class ClientePutRequestDTO {

	private String nomeCompleto;
	private String cpf;
	private LocalDate dataDeNascimento;

	public ClientePutRequestDTO() {
	}

	public ClientePutRequestDTO(String nomeCompleto, String cpf, LocalDate dataDeNascimento) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
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
	
	
}
