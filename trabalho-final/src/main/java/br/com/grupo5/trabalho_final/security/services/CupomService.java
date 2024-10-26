package br.com.grupo5.trabalho_final.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.grupo5.trabalho_final.security.dto.CupomRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.CupomResponseDTO;
import br.com.grupo5.trabalho_final.security.entities.Cupom;
import br.com.grupo5.trabalho_final.security.repositories.CupomRepository;

@Service
public class CupomService {

	@Autowired
	private CupomRepository cupomRepository;

	public Cupom getCupomById(Integer id) {
		Cupom cupom = cupomRepository.findById(id).get();
		return cupom;
	}


	public void createCupom(CupomRequestDTO cupomRequestDTO) {
		Cupom cupom = new Cupom();

		cupom.setCodigo(cupomRequestDTO.getCodigo());
		cupom.setBoolean_ativo(cupomRequestDTO.isBoolean_ativo());
		cupom.setDesconto(cupomRequestDTO.getDesconto());

		cupomRepository.save(cupom);
	}

	public List<Cupom> cupomList() {
		return cupomRepository.findAll();
	}

	public boolean cupomDelete(Integer id) {
		if (cupomRepository.existsById(id)) {
			cupomRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public String alterandoCupom(Integer id, CupomResponseDTO cupom) {
		if(!cupomRepository.existsById(id)) {
			throw new RuntimeException("Cupom não encontrado!");
		} else {
			Cupom novoCupom = cupomRepository.findById(id).get();
			if (cupom.getCodigo() != null) {
			novoCupom.setCodigo(cupom.getCodigo());
			}
			if (cupom.getDesconto() != null) {
				novoCupom.setDesconto(cupom.getDesconto());;
			}
			novoCupom.setBoolean_ativo(cupom.isBoolean_ativo());
			
			cupomRepository.save(novoCupom);
			
			return "Cupom alterado com sucesso!";
		}
}

}