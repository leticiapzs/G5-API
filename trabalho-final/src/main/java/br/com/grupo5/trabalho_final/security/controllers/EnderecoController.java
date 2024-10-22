package br.com.grupo5.trabalho_final.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.grupo5.trabalho_final.security.dto.EnderecoRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.EnderecoResponseDTO;
import br.com.grupo5.trabalho_final.security.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
//  @GetMapping
//  public String getAllEnderecos() {
//    return "All Addresses";
//  }
 
  @PostMapping("/adicionando-endereco")
  public EnderecoResponseDTO testeEndereco(@RequestBody EnderecoRequestDTO enderecoDto) {
	  return enderecoService.consultarEndereco(enderecoDto);  
  }

}
