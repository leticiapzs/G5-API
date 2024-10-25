package br.com.grupo5.trabalho_final.security.controllers;

import java.io.IOException;
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

import br.com.grupo5.trabalho_final.security.dto.ClientePutRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.ClienteRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.ClienteResponseDTO;
import br.com.grupo5.trabalho_final.security.services.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all-clientes")
	public List<ClienteResponseDTO> getAllClientes() {
		return clienteService.allClients();
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(clienteService.getClienteById(id));
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}

	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastroCliente(@RequestBody ClienteRequestDTO clienteDTO) throws IOException {
		return clienteService.cadastrarCliente(clienteDTO);
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@DeleteMapping("/delete-id/{id}")
	public ResponseEntity<String> deleteClienteById(@PathVariable Integer id) {
		boolean resultDelete = clienteService.deleteClienteById(id);
		if (resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Objeto excluído com sucesso.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao excluir objeto.");
		}
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping("/alterar-cliente/{cpf}")
	public ResponseEntity<?> updateClienteById(@PathVariable String cpf, @RequestBody ClientePutRequestDTO clientedto) {
		return clienteService.updateClienteById(cpf, clientedto);
	}
}
