package br.com.grupo5.trabalho_final.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.grupo5.trabalho_final.security.dto.CupomRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.ProdutoRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Cupom;
import br.com.grupo5.trabalho_final.security.entities.Produto;
import br.com.grupo5.trabalho_final.security.repositories.CupomRepository;

public class CupomService {

	@Autowired
	private CupomRepository cupomRepository;

	public Cupom getCupomById(Integer id) {
		Cupom cupom = cupomRepository.findById(id).get();
		return cupom;
	}

	public ResponseEntity<?> createCupom(CupomRequestDTO cupomRequestDTO) {
		Cupom cupom = new Cupom();

		cupom.setCodigo(cupomRequestDTO.getCodigo());
		cupom.setBoolean_ativo(cupomRequestDTO.isBoolean_ativo());
		cupom.setDesconto(cupomRequestDTO.getDesconto());

		return ResponseEntity.ok(cupomRepository.save(cupom));
	}

}