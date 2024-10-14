package br.com.grupo5.trabalho_final.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("role")
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(RoleEnum name);
}
