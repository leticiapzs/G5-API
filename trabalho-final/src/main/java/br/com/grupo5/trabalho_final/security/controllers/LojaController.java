package br.com.grupo5.trabalho_final.security.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.grupo5.trabalho_final.security.dto.LojaPutRequestDTO;
import br.com.grupo5.trabalho_final.security.dto.LojaRequestDTO;
import br.com.grupo5.trabalho_final.security.entities.Loja;
import br.com.grupo5.trabalho_final.security.services.FotoService;
import br.com.grupo5.trabalho_final.security.services.LojaService;

@RestController
@RequestMapping("/loja")
public class LojaController {
	@Autowired
	private LojaService lojaService;
	
	@Autowired
	FotoService fotoService;

	@GetMapping
	public ResponseEntity<?> getAllLojas() {
		return lojaService.getAllLojas();

	}

	@GetMapping("/{id}/loja-foto")
	public ResponseEntity<?> getLojaById(@PathVariable Integer id) {
		try {
			Loja loja = lojaService.getLojaById(id);
			return ResponseEntity.ok(loja);
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}

	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastroLoja(@RequestPart LojaRequestDTO lojadto, @RequestPart MultipartFile foto)
			throws IOException {
		return lojaService.cadastrarLoja(lojadto, foto);

	}

	@DeleteMapping("/deleteId/{id}")
	public ResponseEntity<String> deletarId(@PathVariable Integer id) {
		boolean resultDelete = lojaService.lojaDelete(id);
		if (resultDelete) {
			return ResponseEntity.status(HttpStatus.OK).body("Objeto excluído com sucesso.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao excluir objeto.");
		}
	}

	@PutMapping("/alterar-loja/")
	public ResponseEntity<?> alteraLoja(@PathVariable String cnpj, @RequestBody LojaPutRequestDTO lojadto) {
		return lojaService.alterarLoja(cnpj, lojadto);
	}
	
	@GetMapping("/{idLoja}/foto")
	public ResponseEntity<byte[]> retornoDaFoto (@PathVariable Integer idLoja) throws Exception{
		byte[] foto = fotoService.getFoto(idLoja);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(foto);
	}
}
