package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

  @GetMapping
  public String getAllProducts() {
    return "All Products";
  }

}
