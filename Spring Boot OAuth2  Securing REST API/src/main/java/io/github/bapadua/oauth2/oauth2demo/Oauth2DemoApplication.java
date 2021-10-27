package io.github.bapadua.oauth2.oauth2demo;

import io.github.bapadua.oauth2.oauth2demo.domain.Role;
import io.github.bapadua.oauth2.oauth2demo.domain.RolesConst;
import io.github.bapadua.oauth2.oauth2demo.domain.User;
import io.github.bapadua.oauth2.oauth2demo.repository.RoleRepository;
import io.github.bapadua.oauth2.oauth2demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Oauth2DemoApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Oauth2DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {

		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {
			createUser("Admin", "admin", passwordEncoder.encode("123456"), RolesConst.ROLE_ADMIN);
			createUser("Cliente", "cliente", passwordEncoder.encode("123456"), RolesConst.ROLE_CLIENT);
		}
	}

	public void createUser(String name, String email, String password, String roleName) {
		Role role = new Role(roleName);
		this.roleRepository.save(role);
		User user = new User(name, email, password, List.of(role));
		userRepository.save(user);
	}
}
