package br.com.grupo5.trabalho_final.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.security.dto.LojaRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Loja;
import br.com.grupo5.trabalho_final.security.services.LojaService;

@RestController
@RequestMapping("/loja")
public class LojaController {
	@Autowired
	private LojaService lojaService;

	@GetMapping
	public List<Loja> getAllLojas() {
		return lojaService.getAllLojas();

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getLojaById(@PathVariable Integer id) {
		try {
			Loja loja = lojaService.getLojaById(id);
			return ResponseEntity.ok(loja);
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}

	@PostMapping("/cadastro")
	public String cadastroLoja(@RequestBody LojaRequestDTO lojadto) {
		lojaService.cadastrarLoja(lojadto);
		return "funcionou";

	}

	@DeleteMapping("/deleteId/{id}")
	public ResponseEntity<String> deletarId(@PathVariable Integer id) {
		boolean resultDelete = lojaService.lojaDelete(id);
		if (resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Objeto deletado com sucesso");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao deletar objeto");
		}

	}
}
