package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.grupo5.trabalho_final.security.dto.ProdutoRequestDTO;
import br.com.grupo5.trabalho_final.security.services.ProdutoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public String getAllProducts() {
    return "All Products";
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@RequestParam Integer id) {
    return produtoService.getProductById(id);
  }

  @PostMapping("/create")
  public ResponseEntity<?> createProduct(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
    return produtoService.createProduct(produtoRequestDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProductById(@PathVariable Integer id,
      @RequestBody ProdutoRequestDTO produtoRequestDTO) {
    return produtoService.updateProductById(id, produtoRequestDTO);
  }

}
