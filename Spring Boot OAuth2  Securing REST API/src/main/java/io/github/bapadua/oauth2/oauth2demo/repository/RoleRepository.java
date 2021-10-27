package io.github.bapadua.oauth2.oauth2demo.repository;

import io.github.bapadua.oauth2.oauth2demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}