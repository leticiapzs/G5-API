package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

// import br.com.grupo5.trabalho_final.security.dto.UserRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.UserResponseDTO;
import br.com.grupo5.trabalho_final.security.services.UserService;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/{id}")
  public UserResponseDTO findUserById(@RequestParam Integer id) {
    return userService.findUserById(id);
  }

  @DeleteMapping("/{id}")
  public String deleteUserById(@RequestParam Integer id) {
    return userService.deleteUserById(id);
  }

  // @PutMapping("/{id}")
  // public String putMethodName(@PathVariable Integer id, @RequestBody
  // UserRequestDTO entity) {
  // return userService.updateUserById(id, entity);
  // }
}