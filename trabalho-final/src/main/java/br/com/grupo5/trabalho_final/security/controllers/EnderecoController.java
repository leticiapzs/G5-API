package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.grupo5.trabalho_final.security.dto.EnderecoRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.EnderecoResponseDTO;
import br.com.grupo5.trabalho_final.security.services.EnderecoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('USER', 'MOD', 'ADMIN')")
	@PostMapping("/adicionando-endereco")
	public EnderecoResponseDTO cadastrarEndereco(@RequestBody EnderecoRequestDTO enderecoDto) {
		return enderecoService.cadastrarEndereco(enderecoDto);
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('USER', 'MOD', 'ADMIN')")
	@GetMapping("/buscando-endereco/{id}")
	public EnderecoResponseDTO buscarEndereco(@PathVariable Integer id) {
		return enderecoService.buscarEndereco(id);
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('USER', 'MOD', 'ADMIN')")
	@DeleteMapping("/deletando-endereco/{id}")
	public ResponseEntity<String> deletarId(@PathVariable Integer id) {
		boolean resultDelete = enderecoService.enderecoDelete(id);
		if (resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço não deletado!");
		}

	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('USER', 'MOD', 'ADMIN')")
	@PutMapping("/alterando-endereco/{id}")
	public String alteracaoEndereco(@PathVariable Integer id, @RequestBody EnderecoResponseDTO endereco) {
		return enderecoService.alteracaoEndereco(id, endereco);
	}

}
