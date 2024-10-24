package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.security.dto.PedidoRequestDTO;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

  @GetMapping
  public String getAllPedidos() {
    return "All Orders";
  }
  
  @PostMapping("/novo-pedido")
  public PedidoResponseDTO novoPedido (Integer id, PedidoRequestDTO pedidoDTO) {
	  
  }

}
