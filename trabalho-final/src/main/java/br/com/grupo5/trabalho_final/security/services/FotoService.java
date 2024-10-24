package br.com.grupo5.trabalho_final.security.services;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		fotoLoja.setUrl(adicionarImagemUri(loja));
		
		return fotoRepository.save(fotoLoja);
	}

	private String adicionarImagemUri(Loja loja) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/loja/{id}/loja-foto")
                .buildAndExpand(loja.getId()).toUri();
		return uri.toString();
	}
	
	@Transactional(readOnly = true)
	public byte[] getFoto(Integer idLoja) throws Exception {
		Foto foto = fotoRepository.buscarFotoPorIdDaLoja(idLoja);
		if (foto == null) {
			throw new Exception("Não existe uma foto cadastrada para a loja de id " + idLoja);
		}
		return foto.getDados();
	}
}
