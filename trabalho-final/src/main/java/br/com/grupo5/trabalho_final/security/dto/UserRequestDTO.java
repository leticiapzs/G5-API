package br.com.grupo5.trabalho_final.security.dto;

public class UserRequestDTO {
  private String username;
  private String email;

  public UserRequestDTO(Integer id, String username, String email, String password) {
    this.username = username;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }
}
