package br.com.grupo5.trabalho_final.security.entities;

import java.time.LocalDate;
import java.util.List;

import br.com.grupo5.trabalho_final.security.dto.ClienteResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_cd_id")
	private Integer id;

	@Column(name = "cli_tx_nome")
	private String nomeCompleto;

	@Column(name = "cli_tx_cpf")
	private String cpf;

	@OneToMany
	@JoinColumn(name = "cli_fk_end")
	private List<Endereco> fkEndereco;

	@Column(name = "cli_dt_nasc")
	private LocalDate dataDeNascimento;

	@OneToOne
	@JoinColumn(name = "cli_fk_user")
	private User user;

	public Cliente() {
	}

	public Cliente(Integer id, String nomeCompleto, String cpf, List<Endereco> fkEndereco, LocalDate dataDeNascimento,
			User user) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.fkEndereco = fkEndereco;
		this.dataDeNascimento = dataDeNascimento;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Endereco> getFkEndereco() {
		return fkEndereco;
	}

	public void setFkEndereco(List<Endereco> fkEndereco) {
		this.fkEndereco = fkEndereco;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public ClienteResponseDTO toResponseDTO () {
		return new ClienteResponseDTO(this.nomeCompleto, this.cpf, this.dataDeNascimento, this.user.getUsername(), this.user.getEmail(), this.fkEndereco);
	}

}
