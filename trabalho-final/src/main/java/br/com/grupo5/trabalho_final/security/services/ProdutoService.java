package br.com.grupo5.trabalho_final.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.MessageResponseDTO;
import br.com.grupo5.trabalho_final.security.dto.ProdutoRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.ProdutoResponseDTO;
import br.com.grupo5.trabalho_final.security.entities.Categoria;
import br.com.grupo5.trabalho_final.security.entities.Produto;
import br.com.grupo5.trabalho_final.security.repositories.CategoriaRepository;
import br.com.grupo5.trabalho_final.security.repositories.LojaRepository;
import br.com.grupo5.trabalho_final.security.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private LojaRepository lojaRepository;

	public List<ProdutoResponseDTO> getAllProducts() {
		List<ProdutoResponseDTO> lista = new ArrayList<>();
		for (Produto produto : produtoRepository.findAll()) {
			lista.add(produto.toResponseDTO());
		}
		return lista;
	}

	public ResponseEntity<?> getProductById(Integer id) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoRepository.findById(id).get().toResponseDTO());
	}

	public ResponseEntity<?> createProduct(ProdutoRequestDTO produtoRequestDTO) {
		Optional<Categoria> categoriaOptional = Optional
				.ofNullable(categoriaRepository.findByName(produtoRequestDTO.getCategoria()));
		Categoria categoria = categoriaOptional.orElseGet(() -> {
			Categoria newCategoria = new Categoria();
			newCategoria.setNome(produtoRequestDTO.getCategoria());
			return categoriaRepository.save(newCategoria);
		});

		Produto produto = new Produto();
		produto.setCategoria(categoria);
		produto.setNome(produtoRequestDTO.getNome());
		produto.setDescricao(produtoRequestDTO.getDescricao());
		produto.setValor(produtoRequestDTO.getValor());
		produto.setEstoque(produtoRequestDTO.getEstoque());
		produto.setLoja(lojaRepository.findById(produtoRequestDTO.getLoja()).get());

		produtoRepository.save(produto);

		return ResponseEntity.ok(new MessageResponseDTO("Produto cadastrado com sucesso!"));
	}

	public ResponseEntity<?> updateProductById(Integer id, ProdutoResponseDTO produtoResponseDTO) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Produto produto = produtoRepository.findById(id).get();

		if (produtoResponseDTO.getNome() != null) {
			produto.setNome(produtoResponseDTO.getNome());
		}
		if (produtoResponseDTO.getDescricao() != null) {
			produto.setDescricao(produtoResponseDTO.getDescricao());
		}
		if (produtoResponseDTO.getValor() != null) {
			produto.setValor(produtoResponseDTO.getValor());
		}
		if (produtoResponseDTO.getEstoque() != null) {
			produto.setEstoque(produtoResponseDTO.getEstoque());
		}

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
