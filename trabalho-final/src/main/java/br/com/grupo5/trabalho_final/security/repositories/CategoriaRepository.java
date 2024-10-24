package br.com.grupo5.trabalho_final.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.grupo5.trabalho_final.security.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

  @Query("SELECT c FROM Categoria c WHERE c.nome = ?1")
  Categoria findByName(String nome);

}
