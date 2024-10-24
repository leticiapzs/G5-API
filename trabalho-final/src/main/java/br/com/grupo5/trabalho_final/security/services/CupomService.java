package br.com.grupo5.trabalho_final.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.example.atores.security.dto.SeriesResponseDTO;
import br.com.example.atores.security.entities.Series;
import br.com.grupo5.trabalho_final.security.dto.CupomRequestDTO;
<<<<<<< HEAD
import br.com.grupo5.trabalho_final.security.dto.CupomResponseDTO;
=======
>>>>>>> 4f8892e6e971dcccf1a9ec5efaf167732661a63c
import br.com.grupo5.trabalho_final.security.entities.Cupom;
import br.com.grupo5.trabalho_final.security.repositories.CupomRepository;

public class CupomService {

	@Autowired
	private CupomRepository cupomRepository;

	public Cupom getCupomById(Integer id) {
		Cupom cupom = cupomRepository.findById(id).get();
		return cupom;
	}

//	public ResponseEntity<?> createCupom(CupomRequestDTO cupomRequestDTO) {
//		Cupom cupom = new Cupom();
//
//		cupom.setCodigo(cupomRequestDTO.getCodigo());
//		cupom.setBoolean_ativo(cupomRequestDTO.isBoolean_ativo());
//		cupom.setDesconto(cupomRequestDTO.getDesconto());
//
//		return ResponseEntity.ok(cupomRepository.save(cupom));
//	}
	
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
		if(cupomRepository.existsById(id)) {
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
			try {
				Cupom cupom = cupomRepository.findById(id).get();
				cupomResponseDTO.setTitulo(cupom.getTitulo());
				cupomResponseDTO.setGenero(cupom.getGenero());
				cupomResponseDTO.setAnoLancamento(cupom.getAnoLancamento());
				cupomRepository.save(cupomResponseDTO);
				return "Cupom alterado com sucesso!";
			} catch(Exception e) {
				throw new RuntimeException("Cupom não pode ser alterado!");
			}
		}
	}

}