package br.com.grupo5.trabalho_final.security.dto;

import java.util.Set;
import br.com.grupo5.trabalho_final.security.entities.Role;

public class UserResponseDTO {
  private String username;
  private String email;
  private Set<Role> roles;

  public UserResponseDTO(String username, String email, Set<Role> roles) {
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public Set<Role> getRoles() {
    return roles;
  }
}