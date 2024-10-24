package br.com.grupo5.trabalho_final.security.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.grupo5.trabalho_final.security.dto.LojaPutRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.LojaRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.LojaResponseDTO;
import br.com.grupo5.trabalho_final.security.dto.MessageResponseDTO;
import br.com.grupo5.trabalho_final.security.dto.SignupRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Endereco;
import br.com.grupo5.trabalho_final.security.entities.Loja;
import br.com.grupo5.trabalho_final.security.entities.User;
import br.com.grupo5.trabalho_final.security.repositories.EnderecoRepository;
import br.com.grupo5.trabalho_final.security.repositories.LojaRepository;

@Service
public class LojaService {

	@Autowired
	private LojaRepository lojaRepository;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	AuthenticationService authService;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	FotoService fotoService;

	@SuppressWarnings({ "null", "unused" })
	public ResponseEntity<?> getAllLojas() {
		List<LojaResponseDTO> lista = null;
		for (Loja loja : lojaRepository.findAll()) {
			lista.add(loja.toResponseDTO());
		}
		if (lista == null) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(lista);
	}

	public Loja getLojaById(Integer id) {
		Loja loja = lojaRepository.findById(id).get();
		return loja;
	}

	public ResponseEntity<?> cadastrarLoja(LojaRequestDTO lojaDTO, MultipartFile foto) throws IOException {
		// cria o endereço
		Endereco endereco = enderecoService.criarEndereco(lojaDTO.toEnderecoDTO());

		// cria um DTO para o método de signup autenticado
		SignupRequestDTO userDTO = lojaDTO.toSingupDTO();

		// adiciona a role pertinente
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
		fotoService.cadastrarFoto(foto, loja);

		return ResponseEntity.ok(new MessageResponseDTO("Loja cadastrada com sucesso"));
	}

	public boolean lojaDelete(Integer id) {
		if (lojaRepository.existsById(id)) {
			lojaRepository.deleteById(id);
			return true;
		} else {
			return false;

		}
	}

	public ResponseEntity<?> alterarLoja(String cnpj, LojaPutRequestDTO lojaDTO) {

		Loja loja = lojaRepository.findByCnpj(cnpj).get();

		if (lojaDTO.getCnpj() != null) {
			loja.setCnpj(lojaDTO.getCnpj());
		}
		if (lojaDTO.getNomeFantasia() != null) {
			loja.setNomeFantasia(lojaDTO.getNomeFantasia());
		}

		lojaRepository.save(loja);

		return ResponseEntity.ok(new MessageResponseDTO("Informações da loja alteradas com sucesso."));
	}
}
