package br.com.grupo5.trabalho_final.security.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "foto")
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ft_cd_id")
	private Integer id;
	
	@Column(name = "ft_tx_tipo")
	private String tipo;
	
	@Column(name = "ft_tx_nome")
	private String nome;
	
	@Lob
	@Column(name = "ft_bt_dados")
	private byte[] dados;
	
	@Column(name = "ft_tx_url")
	private String url;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "loj_cd_id")
	private Loja loja;
	
	public Foto() {
	}

	public Foto(Integer id, String tipo, String nome, byte[] dados, Loja loja, String url) {
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.dados = dados;
		this.loja = loja;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
