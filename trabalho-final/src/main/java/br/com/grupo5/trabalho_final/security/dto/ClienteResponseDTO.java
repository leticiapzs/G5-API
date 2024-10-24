package br.com.grupo5.trabalho_final.security.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.grupo5.trabalho_final.security.entities.Endereco;

public class ClienteResponseDTO {
	private String nomeCompleto;
	private String cpf;
	private LocalDate dataDeNascimento;
	private String username;
	private String email;
	private List<EnderecoResponseDTO> endereco;
	
	public ClienteResponseDTO(String nomeCompleto, String cpf, LocalDate dataDeNascimento, String username,
			String email, List<Endereco> endereco) {
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.username = username;
		this.email = email;
		this.endereco = toEndResponseDTO(endereco);
	}
	
	public List<EnderecoResponseDTO> toEndResponseDTO(List<Endereco> enderecos){
		List<EnderecoResponseDTO> novaLista = new ArrayList<EnderecoResponseDTO>();
		for (Endereco endereco : enderecos) {
			EnderecoResponseDTO endDTO = endereco.toResponseDTO();
			novaLista.add(endDTO);
		}
		return novaLista;
		
	}
	
	public ClienteResponseDTO() {
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

	public List<EnderecoResponseDTO> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<EnderecoResponseDTO> endereco) {
		this.endereco = endereco;
	}	

}
