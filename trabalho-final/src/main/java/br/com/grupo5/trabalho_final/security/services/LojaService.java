package br.com.grupo5.trabalho_final.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.LojaRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Endereco;
import br.com.grupo5.trabalho_final.security.entities.Loja;
import br.com.grupo5.trabalho_final.security.repositories.LojaRepository;

@Service
public class LojaService {
	@Autowired
	private LojaRepository lojaRepository;
	@Autowired
	EnderecoService enderecoService;

	public List<Loja> getAllLojas() {

		return lojaRepository.findAll();

	}

	public Loja getLojaById(Integer id) {
		Loja loja = lojaRepository.findById(id).get();
		return loja;

	}

	public Loja cadastrarLoja(LojaRequestDTO lojaDTO) {
		Endereco endereco = enderecoService.criarEndereco(lojaDTO.toEnderecoDTO());
		Loja loja = new Loja();
		loja.setCnpj(lojaDTO.getCnpj());
		loja.setNomeFantasia(lojaDTO.getNomeFantasia());
		loja.setFkEndereco(endereco);
		lojaRepository.save(loja);
		return loja;
	}
}
