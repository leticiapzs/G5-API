package br.com.grupo5.trabalho_final.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.grupo5.trabalho_final.security.entities.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {

	Optional<Loja> findByCnpj(String cnpj);
	
	@Query(value = "select l.* from loja l join users u on l.lo_fk_user = u.id where u.username = :username;", nativeQuery = true)
	Loja findByUsername(String username);


}
