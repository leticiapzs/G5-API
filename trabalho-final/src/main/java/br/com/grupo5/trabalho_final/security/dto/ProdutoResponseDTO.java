package br.com.grupo5.trabalho_final.security.dto;

public class ProdutoResponseDTO {

  private String nome;

  private String descricao;

  private Double valor;

  private Integer estoque;

  public ProdutoResponseDTO() {
  }

  public ProdutoResponseDTO(String nome, String descricao, Double valor, Integer estoque) {
    this.nome = nome;
    this.descricao = descricao;
    this.valor = valor;
    this.estoque = estoque;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Integer getEstoque() {
    return estoque;
  }

  public void setEstoque(Integer estoque) {
    this.estoque = estoque;
  }
}
