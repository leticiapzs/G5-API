package br.com.grupo5.trabalho_final.security.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.ClientePutRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.ClienteRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.ClienteResponseDTO;
import br.com.grupo5.trabalho_final.security.dto.MessageResponseDTO;
import br.com.grupo5.trabalho_final.security.dto.SignupRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Cliente;
import br.com.grupo5.trabalho_final.security.entities.Endereco;
import br.com.grupo5.trabalho_final.security.entities.User;
import br.com.grupo5.trabalho_final.security.repositories.ClienteRepository;
import br.com.grupo5.trabalho_final.security.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	AuthenticationService authService;

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	EmailService emailService;
	
	public List<ClienteResponseDTO> allClients() {
		List<ClienteResponseDTO> listaDTO = new ArrayList<ClienteResponseDTO>();
		for (Cliente cliente : clienteRepo.findAll()) {
			ClienteResponseDTO clienteDTO = cliente.toResponseDTO();
			listaDTO.add(clienteDTO);
		}
		return listaDTO;
	}
	
	public ResponseEntity<?> getClienteById (Integer id) {
		if (!clienteRepo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		ClienteResponseDTO cliente = clienteRepo.findById(id).get().toResponseDTO();
		return ResponseEntity.ok(cliente);
	}

	public ResponseEntity<?> cadastrarCliente(ClienteRequestDTO clienteDTO) {
		// cria o endereço
		Endereco endereco = enderecoService.criarEndereco(clienteDTO.toEnderecoDTO());
		enderecoRepository.save(endereco);
		List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		listaEnderecos.add(endereco);
		// cria um DTO para o método de signup autenticado
		SignupRequestDTO userDTO = clienteDTO.toSingupDTO();

		// adiciona a role pertinente
		Set<String> role = new HashSet<>();
		role.add("user");
		userDTO.setRole(role);

		User user = new User();
		try {
			user = (User) authService.novoUser(userDTO).getBody();

		} catch (Exception e) {
			return authService.novoUser(userDTO);
		}

		Cliente cliente = new Cliente();
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
		cliente.setFkEndereco(listaEnderecos);
		cliente.setUser(user);
		cliente.setDataDeNascimento(clienteDTO.getDataDeNascimento());
		clienteRepo.save(cliente);
		emailService.mailCadastroCliente(cliente);

		return ResponseEntity.ok(new MessageResponseDTO("Cliente cadastrado com sucesso."));
	}

	public boolean clienteDelete(Integer id) {
		if (clienteRepo.existsById(id)) {
			clienteRepo.deleteById(id);
			return true;
		} else {
			return false;

		}
	}

	public ResponseEntity<?> alterarCliente(String cpf, ClientePutRequestDTO clienteDTO) {

		Cliente cliente = clienteRepo.findByCpf(cpf).get();

		if (clienteDTO.getCpf() != null) {
			cliente.setCpf(clienteDTO.getCpf());
		}
		if (clienteDTO.getNomeCompleto() != null) {
			cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
		}
		if (clienteDTO.getDataDeNascimento() != null) {
			cliente.setDataDeNascimento(clienteDTO.getDataDeNascimento());
		}

		clienteRepo.save(cliente);

		return ResponseEntity.ok(new MessageResponseDTO("Informações do cliente atualizadas com sucesso."));
	}
}
