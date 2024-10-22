package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

  public String getAllProdutos() {
    return "All Products";
  }

}
