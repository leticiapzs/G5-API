package br.com.grupo5.trabalho_final.security.services;

import java.util.Optional;

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
	
	public EnderecoResponseDTO buscarEndereco(Integer id) {
		Optional <Endereco> endereco = enderecoRepository.findById(id);
		EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();
		enderecoResponseDTO.setCep(endereco.get().getCep());
		enderecoResponseDTO.setLogradouro(endereco.get().getLogradouro());
		enderecoResponseDTO.setNumero(endereco.get().getNumero());
		enderecoResponseDTO.setComplemento(endereco.get().getComplemento());
		enderecoResponseDTO.setBairro(endereco.get().getBairro());
		enderecoResponseDTO.setLocalidade(endereco.get().getLocalidade());
		enderecoResponseDTO.setUf(endereco.get().getUf());
		enderecoResponseDTO.setEstado(endereco.get().getEstado());
		
		return enderecoResponseDTO;
	}
	
	public boolean enderecoDelete(Integer id) {
		if(enderecoRepository.existsById(id)) {
			enderecoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
}
