package br.com.grupo5.trabalho_final.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.grupo5.trabalho_final.security.dto.EnderecoResponseDTO;
import br.com.grupo5.trabalho_final.security.dto.EnderecoRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Endereco;
import br.com.grupo5.trabalho_final.security.repositories.EnderecoRepository;
import br.com.grupo5.trabalho_final.utils.Util;

@Service
public class EnderecoService {

	@Autowired
	Util util;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public EnderecoResponseDTO consultarEndereco(EnderecoRequestDTO enderecoDto) {
		EnderecoResponseDTO viaCep = util.buscarEndereco(enderecoDto.getCep());
		
		EnderecoResponseDTO endereco = new EnderecoResponseDTO();
		endereco.setCep(viaCep.getCep());
		endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setComplemento(enderecoDto.getComplemento());
        endereco.setBairro(viaCep.getBairro());
        endereco.setLocalidade(viaCep.getLocalidade());
        endereco.setUf(viaCep.getUf());
        endereco.setEstado(viaCep.getEstado());
		
		Endereco enderecoConvertido = endereco.toEndereco();
		enderecoRepository.save(enderecoConvertido);
		
		return endereco;
	}
	
	
}
