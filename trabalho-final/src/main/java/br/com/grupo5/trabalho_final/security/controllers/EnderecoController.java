package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

  @GetMapping
  public String getAllEnderecos() {
    return "All Addresses";
  }

}
