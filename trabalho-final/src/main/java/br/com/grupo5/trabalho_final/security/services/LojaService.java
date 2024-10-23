package br.com.grupo5.trabalho_final.security.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.LojaRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.MessageResponseDTO;
import br.com.grupo5.trabalho_final.security.dto.SignupRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Endereco;
import br.com.grupo5.trabalho_final.security.entities.Loja;
import br.com.grupo5.trabalho_final.security.entities.User;
import br.com.grupo5.trabalho_final.security.repositories.LojaRepository;
import br.com.grupo5.trabalho_final.security.repositories.UserRepository;

@Service
public class LojaService {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	AuthenticationService authService;
	
	@Autowired
	UserRepository userRepository;

	public List<Loja> getAllLojas() {
		return lojaRepository.findAll();
	}

	public Loja getLojaById(Integer id) {
		Loja loja = lojaRepository.findById(id).get();
		return loja;
	}

	public ResponseEntity<?> cadastrarLoja(LojaRequestDTO lojaDTO) {
		//cria o endereço
		Endereco endereco = enderecoService.criarEndereco(lojaDTO.toEnderecoDTO());
		
		//cria um DTO para o método de signup autenticado
		SignupRequestDTO userDTO = lojaDTO.toSingupDTO();
		
		//adiciona a role pertinente
		Set<String> role = new HashSet<>();
		role.add("mod");
		userDTO.setRole(role);
		
		User user = new User();
		try {
			user = (User) authService.novoUser(userDTO).getBody(); 
			
		} catch (Exception e) {
			return authService.novoUser(userDTO);
		}
			
			Loja loja = new Loja();
			loja.setCnpj(lojaDTO.getCnpj());
			loja.setNomeFantasia(lojaDTO.getNomeFantasia());
			loja.setFkEndereco(endereco);
			loja.setFkUser(user);
			lojaRepository.save(loja);
		
		
		return ResponseEntity.ok(new MessageResponseDTO("Loja cadastrada com sucesso"));
	}
}
