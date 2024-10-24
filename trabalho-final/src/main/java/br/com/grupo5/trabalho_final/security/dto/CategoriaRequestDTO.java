package br.com.grupo5.trabalho_final.security.dto;

public class CategoriaRequestDTO {
  private String nome;

  public CategoriaRequestDTO() {
  }

  public CategoriaRequestDTO(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public CategoriaRequestDTO nome(String nome) {
    this.nome = nome;
    return this;
  }

  @Override
  public String toString() {
    return "{" +
        " nome='" + getNome() + "'" +
        "}";
  }

}
