package br.com.grupo5.trabalho_final.security.controllers;

import java.util.List;

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

import br.com.grupo5.trabalho_final.security.dto.CupomRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.CupomResponseDTO;
import br.com.grupo5.trabalho_final.security.dto.MessageResponseDTO;
import br.com.grupo5.trabalho_final.security.entities.Cupom;
import br.com.grupo5.trabalho_final.security.services.CupomService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/cupom")
public class CupomController {

	@Autowired
	CupomService cupomService;

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
	@PostMapping("/adicionando-cupom")
	public ResponseEntity<?> createCupom(@RequestBody CupomRequestDTO cupomDto) {
		cupomService.createCupom(cupomDto);
		return ResponseEntity.ok(new MessageResponseDTO("Cupom adicionado!"));
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
	@GetMapping("/listando-cupom")
	public List<Cupom> listCupom() {
		return cupomService.cupomList();
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
	@DeleteMapping("/deletando-cupom/{id}")
	public ResponseEntity<String> deletarId(@PathVariable Integer id) {
		boolean resultDelete = cupomService.cupomDelete(id);
		if (resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Cupom deletado com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cupom não deletado!");
		}
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
	@PutMapping("/atualizando-cupom/{id}")
	public String alterandoCupom(@PathVariable Integer id, @RequestBody CupomResponseDTO cupom) {
		return cupomService.alterandoCupom(id, cupom);
	}

}
