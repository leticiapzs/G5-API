package br.com.grupo5.trabalho_final.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.grupo5.trabalho_final.security.entities.Categoria;
import br.com.grupo5.trabalho_final.security.entities.Role;
import br.com.grupo5.trabalho_final.security.entities.User;
import br.com.grupo5.trabalho_final.security.enums.RoleEnum;
import br.com.grupo5.trabalho_final.security.repositories.CategoriaRepository;
import br.com.grupo5.trabalho_final.security.repositories.RoleRepository;
import br.com.grupo5.trabalho_final.security.repositories.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	CategoriaRepository catRepo;
	
	@Override
	public void run(String... args) {
		
		if (!roleRepo.existsById(1)) {
			Role userRole = new Role();
			userRole.setName(RoleEnum.ROLE_USER);
			roleRepo.save(userRole);
		}
		
		if (!roleRepo.existsById(2)) {
			Role modRole = new Role();
			modRole.setName(RoleEnum.ROLE_MODERATOR);
			roleRepo.save(modRole);
		}
		
		if (!roleRepo.existsById(3)) {
			Role adminRole = new Role();
			adminRole.setName(RoleEnum.ROLE_ADMIN);
			roleRepo.save(adminRole);
		}
		
		if (!userRepo.existsByUsername("admin")) {
			User userAdmin = new User("admin", "admin@email.com", encoder.encode("admin"));
			userAdmin.getRoles().add(roleRepo.findById(3).get());
			userRepo.save(userAdmin);
		}
		
		if (!catRepo.existsById(1)) {
			Categoria catInfo = new Categoria();
			catInfo.setNome("Informática");
			catRepo.save(catInfo);
		}
		
		if (!catRepo.existsById(2)) {
			Categoria catVest = new Categoria();
			catVest.setNome("Vestuário");
			catRepo.save(catVest);
		}
		
		if (!catRepo.existsById(3)) {
			Categoria catIlu = new Categoria();
			catIlu.setNome("Iluminação");
			catRepo.save(catIlu);
		}
		
	}
	
	

}
