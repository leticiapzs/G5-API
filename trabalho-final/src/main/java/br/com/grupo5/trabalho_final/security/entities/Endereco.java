package br.com.grupo5.trabalho_final.security.entities;

import br.com.grupo5.trabalho_final.security.dto.EnderecoResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "endereco")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "logradouro")
	private String logradouro;
	
	@Column(name = "numero")
	private Integer numero;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "localidade")
	private String localidade;
	
	@Column(name = "uf")
	private String uf;
	
	@Column(name = "estado")
	private String estado;

	public Endereco() {
	}

	public Endereco(Integer id, String cep, String logradouro, Integer numero, String complemento, String bairro,
			String localidade, String uf, String estado) {
		super();
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.estado = estado;
	}
	
	public Endereco(String cep, String logradouro, Integer numero, String complemento, String bairro,
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf
				+ ", estado=" + estado + "]";
	}
	
	public EnderecoResponseDTO toResponseDTO(){
		return new EnderecoResponseDTO(this.cep, this.logradouro, this.numero, this.complemento, this.bairro, this.localidade, this.uf, this.estado);
	}


}
