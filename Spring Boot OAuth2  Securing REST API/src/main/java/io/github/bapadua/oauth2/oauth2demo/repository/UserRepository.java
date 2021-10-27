package io.github.bapadua.oauth2.oauth2demo.repository;

import io.github.bapadua.oauth2.oauth2demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}