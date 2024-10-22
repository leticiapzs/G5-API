package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loja")
public class LojaController {

  public String getAllLojas() {
    return "All Stores";
  }

}
