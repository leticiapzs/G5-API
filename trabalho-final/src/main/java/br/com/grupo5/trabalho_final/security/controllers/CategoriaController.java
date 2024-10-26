package br.com.grupo5.trabalho_final.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.security.dto.CategoriaRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Categoria;
import br.com.grupo5.trabalho_final.security.services.CategoriaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping("/all")
  public List<Categoria> getAllCategorias() {
    return categoriaService.getAllCategorias();
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/create")
  public ResponseEntity<?> createCategory(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
    return categoriaService.createCategory(categoriaRequestDTO);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("update/{id}")
  public ResponseEntity<?> updateCategory(@PathVariable String id,
      @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
    return categoriaService.updateCategory(id, categoriaRequestDTO);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("delete/{id}")
  public ResponseEntity<?> deleteCategory(@PathVariable String id) {
    return categoriaService.deleteCategory(id);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
  @GetMapping("/{id}")
  public CategoriaRequestDTO getCategoryById(@PathVariable Integer id) {
    return categoriaService.getCategoryById(id);
  }

}
