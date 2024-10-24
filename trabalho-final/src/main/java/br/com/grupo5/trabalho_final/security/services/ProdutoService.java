package br.com.grupo5.trabalho_final.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.ProdutoRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Categoria;
import br.com.grupo5.trabalho_final.security.entities.Produto;
import br.com.grupo5.trabalho_final.security.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public ResponseEntity<?> getProductById(Integer id) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoRepository.findById(id).get());
	}

	public ResponseEntity<?> createProduct(ProdutoRequestDTO produtoRequestDTO) {
		Produto produto = new Produto();
		produto.setCategoria(new Categoria(produtoRequestDTO.getCategoria()));
		produto.setNome(produtoRequestDTO.getNome());
		produto.setDescricao(produtoRequestDTO.getDescricao());
		produto.setValor(produtoRequestDTO.getValor());
		produto.setEstoque(produtoRequestDTO.getEstoque());

		return ResponseEntity.ok(produtoRepository.save(produto));
	}

	public ResponseEntity<?> updateProductById(Integer id, ProdutoRequestDTO produtoRequestDTO) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Produto produto = produtoRepository.findById(id).get();
		produto.setNome(produtoRequestDTO.getNome());
		produto.setDescricao(produtoRequestDTO.getDescricao());
		produto.setCategoria(new Categoria(produtoRequestDTO.getCategoria()));
		produto.setValor(produtoRequestDTO.getValor());
		produto.setEstoque(produtoRequestDTO.getEstoque());
		produto.setCategoria(new Categoria(produtoRequestDTO.getCategoria()));

		return ResponseEntity.ok(produtoRepository.save(produto));
	}

	public boolean deleteProductById(Integer id) {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
