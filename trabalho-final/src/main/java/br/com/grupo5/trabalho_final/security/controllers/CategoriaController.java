package br.com.grupo5.trabalho_final.security.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.security.dto.CategoriaRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Categoria;
import br.com.grupo5.trabalho_final.security.services.CategoriaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('USER')")
  @GetMapping
  public List<Categoria> getAllCategorias() {
    return categoriaService.getAllCategorias();
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('USER')")
  @PostMapping("/create")
  public String createCategory(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
    return categoriaService.createCategory(categoriaRequestDTO);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('USER')")
  @PutMapping("update/{id}")
  public String updateCategory(@PathVariable String id, @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
    return categoriaService.updateCategory(id, categoriaRequestDTO);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('USER')")
  @DeleteMapping("delete/{id}")
  public String deleteCategory(@PathVariable String id) {
    return categoriaService.deleteCategory(id);
  }

  @SecurityRequirement(name = "Bearer Auth")
  @PreAuthorize("hasRole('USER')")
  @GetMapping("/{id}")
  public CategoriaRequestDTO getCategoryById(@RequestParam Integer id) {
    return categoriaService.getCategoryById(id);
  }

}
