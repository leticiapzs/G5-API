package br.com.grupo5.trabalho_final.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.grupo5.trabalho_final.security.entities.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Integer> {
	
	@Query(value = "select * from foto where loj_cd_id = :idLoja", nativeQuery = true)
	Foto buscarFotoPorIdDaLoja(Integer idLoja);
}
