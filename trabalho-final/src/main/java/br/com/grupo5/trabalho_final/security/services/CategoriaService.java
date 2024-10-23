package br.com.grupo5.trabalho_final.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.CategoriaRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.MessageResponseDTO;
import br.com.grupo5.trabalho_final.security.entities.Categoria;

import br.com.grupo5.trabalho_final.security.repositories.CategoriaRepository;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  public List<Categoria> getAllCategorias() {
    return categoriaRepository.findAll();
  }

  public ResponseEntity<?> createCategory(CategoriaRequestDTO categoriaDTO) {
    try {
      Categoria categoria = new Categoria();
      categoria.setNome(categoriaDTO.getNome());
      categoria.setDescricao(categoriaDTO.getDescricao());
      categoriaRepository.save(categoria);
      return ResponseEntity.ok(new MessageResponseDTO("Categoria criada com sucesso!"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro ao criar categoria: " + e.getMessage()));
    }
  }

  public ResponseEntity<?> updateCategory(String id, CategoriaRequestDTO categoriaDTO) {
    try {
      Categoria categoria = categoriaRepository.findById(Integer.parseInt(id)).get();
      categoria.setNome(categoriaDTO.getNome());
      categoria.setDescricao(categoriaDTO.getDescricao());
      categoriaRepository.save(categoria);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro ao atualizar categoria: " + e.getMessage()));
    }
  }

  public ResponseEntity<?> deleteCategory(String id) {
    try {
      Categoria categoria = categoriaRepository.findById(Integer.parseInt(id))
          .orElseThrow(() -> new RuntimeException("Erro: Categoria não encontrada."));
      categoriaRepository.deleteById(categoria.getId());
      return ResponseEntity.ok(new MessageResponseDTO("Categoria deletada com sucesso!"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro ao deletar categoria: " + e.getMessage()));
    }
  }

  public CategoriaRequestDTO getCategoryById(Integer id) {
    Categoria categoria = categoriaRepository.findById(id).get();
    CategoriaRequestDTO categoriaDTO = new CategoriaRequestDTO();
    categoriaDTO.setNome(categoria.getNome());
    categoriaDTO.setDescricao(categoria.getDescricao());
    return categoriaDTO;
  }
}
