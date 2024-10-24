package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.security.dto.PedidoRequestDTO;
import br.com.grupo5.trabalho_final.security.services.PedidoService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @GetMapping("/lista-pedidos/{id}")
  public ResponseEntity<?> getAllPedidos(@RequestParam Integer id) {
    return pedidoService.getAllPedidos(id);
  }

  @GetMapping("/lista-produtos/{id}")
  public String listaProduto(@RequestParam Integer id) {
    return pedidoService.listaProdutos(id);
  }

  @PostMapping("/novo-pedido")
  public ResponseEntity<?> novoPedido(Integer id, PedidoRequestDTO pedidoDTO) {
    return pedidoService.adicionarProduto(pedidoDTO);
  }

  @DeleteMapping("/delete-pedido/{id}")
  public ResponseEntity<String> deletarPedido(@RequestParam Integer id) {
    boolean resultDelete = pedidoService.pedidoDelete(id);
    if (resultDelete) {
      return ResponseEntity.status(HttpStatus.OK).body("Pedido excluído com sucesso.");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao excluir pedido.");
    }
  }

}
