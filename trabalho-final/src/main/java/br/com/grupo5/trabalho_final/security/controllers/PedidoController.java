package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

  public String getAllPedidos() {
    return "All Orders";
  }

}
