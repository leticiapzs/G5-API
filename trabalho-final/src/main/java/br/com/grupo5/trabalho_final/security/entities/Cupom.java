package br.com.grupo5.trabalho_final.security.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cupom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cup_cd_id_loja")
	private Integer idLoja;
	
	@Column(name = "cup_cd_desconto")
	private Integer desconto;
	
	@Column(name = "cup_tx_codigo")
	private String codigo;
	
	@Column(name = "cup_bool_boolean_ativo")
	private boolean boolean_ativo;

	public Cupom() {
	}

	public Cupom(Integer idLoja, Integer desconto, String codigo, boolean boolean_ativo) {
		this.idLoja = idLoja;
		this.desconto = desconto;
		this.codigo = codigo;
		this.boolean_ativo = boolean_ativo;
	}

	public Integer getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Integer idLoja) {
		this.idLoja = idLoja;
	}

	public Integer getDesconto() {
		return desconto;
	}

	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isBoolean_ativo() {
		return boolean_ativo;
	}

	public void setBoolean_ativo(boolean boolean_ativo) {
		this.boolean_ativo = boolean_ativo;
	}

	@Override
	public String toString() {
		return "Cupom [idLoja=" + idLoja + ", desconto=" + desconto + ", codigo=" + codigo + ", boolean_ativo="
				+ boolean_ativo + "]";
	}
	
	
}
