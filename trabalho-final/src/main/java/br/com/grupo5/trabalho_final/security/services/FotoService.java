package br.com.grupo5.trabalho_final.security.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.grupo5.trabalho_final.security.entities.Foto;
import br.com.grupo5.trabalho_final.security.entities.Loja;
import br.com.grupo5.trabalho_final.security.repositories.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	FotoRepository fotoRepository;
	public Foto cadastrarFoto(MultipartFile foto, Loja loja) throws IOException {
		Foto fotoLoja = new Foto();
		fotoLoja.setDados(foto.getBytes());
		fotoLoja.setTipo(foto.getContentType());
		fotoLoja.setNome(foto.getOriginalFilename());
		fotoLoja.setLoja(loja);
		
		return fotoRepository.save(fotoLoja);
	}

}
