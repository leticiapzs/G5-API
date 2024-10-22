package br.com.grupo5.trabalho_final.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.grupo5.trabalho_final.security.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
