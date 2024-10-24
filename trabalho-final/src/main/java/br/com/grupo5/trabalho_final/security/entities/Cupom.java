package br.com.grupo5.trabalho_final.security.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Cupom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cup_cd_id_loja")
	private Integer idLoja;
	
	@Column(name = "cup_cd_desconto")
	private Integer desconto;
	
	@Column(name = "cup_tx_codigo")
	private Integer codigo;
	
	@Column(name = "cup_bool_boolean_ativo")
	private boolean boolean_ativo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cup_fk_loja")
	private Loja fkLoja;
	

	public Cupom() {
	}

	public Cupom(Integer idLoja, Integer desconto, Integer codigo, boolean boolean_ativo, Loja fkLoja) {
		this.idLoja = idLoja;
		this.desconto = desconto;
		this.codigo = codigo;
		this.boolean_ativo = boolean_ativo;
		this.fkLoja = fkLoja;
	}
	
	public Cupom(Integer desconto, Integer codigo, boolean boolean_ativo) {
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
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public boolean isBoolean_ativo() {
		return boolean_ativo;
	}
	
	public void setBoolean_ativo(boolean boolean_ativo) {
		this.boolean_ativo = boolean_ativo;
	}
	
	public Loja getFkLoja() {
		return fkLoja;
	}
	
	public void setFkLoja(Loja fkLoja) {
		this.fkLoja = fkLoja;
	}

	@Override
	public String toString() {
		return "Cupom [idLoja=" + idLoja + ", desconto=" + desconto + ", codigo=" + codigo + ", boolean_ativo="
				+ boolean_ativo + ", fkLoja=" + fkLoja + "]";
	}

}
