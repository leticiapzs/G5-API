package br.com.grupo5.trabalho_final.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.CategoriaRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Categoria;

import br.com.grupo5.trabalho_final.security.repositories.CategoriaRepository;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  public List<Categoria> getAllCategorias() {
    return categoriaRepository.findAll();
  }

  public String createCategory(CategoriaRequestDTO categoriaDTO) {
    try {
      Categoria categoria = new Categoria();
      categoria.setNome(categoriaDTO.getNome());
      categoria.setDescricao(categoriaDTO.getDescricao());
      categoriaRepository.save(categoria);
      return "Categoria criada com sucesso!";
    } catch (Exception e) {
      return "Erro ao criar categoria: " + e.getMessage();
    }
  }

  public String updateCategory(String id, CategoriaRequestDTO categoriaDTO) {
    try {
      Categoria categoria = categoriaRepository.findById(Integer.parseInt(id)).get();
      categoria.setNome(categoriaDTO.getNome());
      categoria.setDescricao(categoriaDTO.getDescricao());
      categoriaRepository.save(categoria);
      return "Categoria atualizada com sucesso!";
    } catch (Exception e) {
      return "Erro ao atualizar categoria: " + e.getMessage();
    }
  }

  public String deleteCategory(String id) {
    try {
      categoriaRepository.deleteById(Integer.parseInt(id));
      return "Categoria deletada com sucesso!";
    } catch (Exception e) {
      return "Erro ao deletar categoria: " + e.getMessage();
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
