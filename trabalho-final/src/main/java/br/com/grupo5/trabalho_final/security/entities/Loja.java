package br.com.grupo5.trabalho_final.security.entities;

import java.util.HashSet;
import java.util.Set;

import br.com.grupo5.trabalho_final.security.dto.LojaResponseDTO;
import jakarta.persistence.CascadeType;
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
@Table(name = "loja")
public class Loja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lo_cd_id")
	private Integer id;

	@Column(name = "lo_tx_cnpj")
	private String cnpj;

	@Column(name = "lo_tx_nome_fantasia")
	private String nomeFantasia;

	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "lo_fk_endereco")
	private Endereco fkEndereco;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "lo_tx_produto")
	private Set<Produto> produtos = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lo_fk_user")
	private User fkUser;

	public Loja() {
	}

	public Loja(String cnpj, String nomeFantasia, Endereco id_endereco, Set<Produto> produtos,
			User fkUser) {
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.fkEndereco = id_endereco;
		this.produtos = produtos;
		this.fkUser = fkUser;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nome_fantasia) {
		this.nomeFantasia = nome_fantasia;
	}

	public Endereco getFkEndereco() {
		return fkEndereco;
	}

	public void setFkEndereco(Endereco fkEndereco) {
		this.fkEndereco = fkEndereco;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

	public User getFkUser() {
		return fkUser;
	}

	public void setFkUser(User fkUser) {
		this.fkUser = fkUser;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "Loja [id=" + id + ", cnpj=" + cnpj + ", nome_fantasia=" + nomeFantasia + ", fkUser=" + fkUser + "]";
	}

	public LojaResponseDTO toResponseDTO() {
		return new LojaResponseDTO(this.getCnpj(), this.getNomeFantasia(), this.fkEndereco.toResponseDTO(), fkUser.getUsername()) ;
	}
}
