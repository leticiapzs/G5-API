package br.com.grupo5.trabalho_final.security.dto;

public class UserRequestDTO {
  private Integer id;
  private String username;
  private String email;
  private String password;

  public UserRequestDTO(Integer id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

}
