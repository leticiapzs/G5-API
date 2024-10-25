package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.grupo5.trabalho_final.security.dto.ProdutoRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.ProdutoResponseDTO;
import br.com.grupo5.trabalho_final.security.services.ProdutoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public String getAllProducts() {
    return "All Products";
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasAnyRole('USER', 'MOD')")
  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@RequestParam Integer id) {
    return produtoService.getProductById(id);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('MOD')")
  @PostMapping("/create")
  public ResponseEntity<?> createProduct(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
    return produtoService.createProduct(produtoRequestDTO);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('MOD')")
  @PutMapping("/{id}")
  public ResponseEntity<?> updateProductById(@PathVariable Integer id,
      @RequestBody ProdutoResponseDTO produtoRequestDTO) {
    return produtoService.updateProductById(id, produtoRequestDTO);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('MOD')")
  @DeleteMapping("/deleteId/{id}")
  public ResponseEntity<String> deleteById(@PathVariable Integer id) {
    boolean resultDelete = produtoService.deleteProductById(id);
    if (resultDelete) {
      return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso.");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao excluir produto.");
    }
  }
}
