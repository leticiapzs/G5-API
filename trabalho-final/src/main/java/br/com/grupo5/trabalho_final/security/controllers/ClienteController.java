package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

  @GetMapping
  public String getAllClientes() {
    return "All Clients";
  }

}
