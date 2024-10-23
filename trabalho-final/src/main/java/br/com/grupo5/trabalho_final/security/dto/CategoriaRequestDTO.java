package br.com.grupo5.trabalho_final.security.dto;

public class CategoriaRequestDTO {
  private String nome;
  private String descricao;

  public CategoriaRequestDTO() {
  }

  public CategoriaRequestDTO(String nome, String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public CategoriaRequestDTO nome(String nome) {
    this.nome = nome;
    return this;
  }

  public CategoriaRequestDTO descricao(String descricao) {
    this.descricao = descricao;
    return this;
  }

  @Override
  public String toString() {
    return "{" +
        " nome='" + getNome() + "'" +
        ", descricao='" + getDescricao() + "'" +
        "}";
  }

}
