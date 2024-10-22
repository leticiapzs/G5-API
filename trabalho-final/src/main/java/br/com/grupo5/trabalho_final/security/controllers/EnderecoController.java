package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupo5.trabalho_final.security.dto.EnderecoRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.EnderecoResponseDTO;
import br.com.grupo5.trabalho_final.security.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@PostMapping("/adicionando-endereco")
	public EnderecoResponseDTO testeEndereco(@RequestBody EnderecoRequestDTO enderecoDto) {
		return enderecoService.cadastrarEndereco(enderecoDto);
	}

	@GetMapping("/buscando-endereco/{id}")
	public EnderecoResponseDTO buscarEndereco(@PathVariable Integer id) {
		return enderecoService.buscarEndereco(id);
	}

	@DeleteMapping("/deletando-endereco/{id}")
	public ResponseEntity<String> deletarId(@PathVariable Integer id) {
		boolean resultDelete = enderecoService.enderecoDelete(id);
		if (resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço não deletado!");
		}

	}

	@PutMapping("/alterando-endereco/{id}")
	public String alteracaoEndereco(@PathVariable Integer id, @RequestBody EnderecoResponseDTO endereco) {
		return enderecoService.alteracaoEndereco(id, endereco);
	}

}
