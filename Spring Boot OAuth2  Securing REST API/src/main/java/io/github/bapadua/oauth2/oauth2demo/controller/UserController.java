package io.github.bapadua.oauth2.oauth2demo.controller;

import io.github.bapadua.oauth2.oauth2demo.domain.RolesConst;
import io.github.bapadua.oauth2.oauth2demo.domain.User;
import io.github.bapadua.oauth2.oauth2demo.repository.RoleRepository;
import io.github.bapadua.oauth2.oauth2demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Secured({RolesConst.ROLE_ADMIN})
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Secured({RolesConst.ROLE_ADMIN})
    @PutMapping
    public ResponseEntity<User> edit(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Secured({RolesConst.ROLE_CLIENT, RolesConst.ROLE_ADMIN})
    @GetMapping
    public ResponseEntity<Page<User>> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return ResponseEntity.ok(userRepository.findAll(pageable));
    }

    @GetMapping("/user-auth")
    @Secured({RolesConst.ROLE_CLIENT, RolesConst.ROLE_ADMIN})
    public User user() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }


}