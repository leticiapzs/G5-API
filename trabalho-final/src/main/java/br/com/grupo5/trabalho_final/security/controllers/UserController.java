package br.com.grupo5.trabalho_final.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.security.dto.UserRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.UserResponseDTO;
import br.com.grupo5.trabalho_final.security.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/all")
  public List<UserResponseDTO> allAccess() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('USER', 'MOD')")
  public UserResponseDTO findUserById(@RequestParam Integer id) {
    return userService.findUserById(id);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasAnyRole('USER', 'MOD')")
  @DeleteMapping("/{id}")
  public String deleteUserById(@PathVariable Integer id) {
    return userService.deleteUserById(id);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasAnyRole('USER', 'MOD')")
  @PutMapping("/{id}")
  public String updateUserById(@PathVariable Integer id, @RequestBody UserRequestDTO user) {
    return userService.updateUserById(id, user);
  }
}
