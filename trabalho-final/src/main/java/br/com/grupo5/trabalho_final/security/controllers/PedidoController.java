package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.security.dto.PedidoRequestDTO;
import br.com.grupo5.trabalho_final.security.services.PedidoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
  @GetMapping("/lista-pedidos/{id}")
  public ResponseEntity<?> getAllPedidos(@RequestParam Integer id) {
    return pedidoService.getAllPedidos(id);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
  @GetMapping("/lista-produtos/{id}")
  public String listaProduto(@RequestParam Integer id) {
    return pedidoService.listaProdutos(id);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
  @PostMapping("/novo-pedido")
  public ResponseEntity<?> novoPedido(PedidoRequestDTO pedidoDTO) {
    return pedidoService.adicionarProduto(pedidoDTO);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
  @PutMapping("/update-pedido/{id}")
  public ResponseEntity<?> updatePedidoById(@RequestParam Integer id, @RequestBody PedidoRequestDTO pedidoDTO) {
    return pedidoService.updatePedidoById(id, pedidoDTO);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
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
