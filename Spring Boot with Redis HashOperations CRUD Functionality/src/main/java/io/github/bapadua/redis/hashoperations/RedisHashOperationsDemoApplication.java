package io.github.bapadua.redis.hashoperations;

import io.github.bapadua.redis.hashoperations.domain.User;
import io.github.bapadua.redis.hashoperations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class RedisHashOperationsDemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RedisHashOperationsDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = User.builder().userId(1L).name("Test user").build();
		userRepository.create(user);
		User found = (User) userRepository.get(user.getUserId());
		System.out.println(found.getName());
		User update = User.builder()
				.userId(user.getUserId())
				.name("Updated User")
				.build();
		userRepository.update(update);
		User updated = (User) userRepository.get(user.getUserId());
		System.out.println(updated.getName());

//		userRepository.delete("1");

		Map<Long, User> all = userRepository.getAllTest();



		System.out.println(all.isEmpty());
	}
}
