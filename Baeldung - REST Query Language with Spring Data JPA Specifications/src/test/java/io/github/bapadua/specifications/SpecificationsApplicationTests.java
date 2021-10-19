package io.github.bapadua.specifications;

import io.github.bapadua.specifications.entities.User;
import io.github.bapadua.specifications.repositories.SearchCriteria;
import io.github.bapadua.specifications.repositories.UserRepository;
import io.github.bapadua.specifications.repositories.UserSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class SpecificationsApplicationTests {

    @Autowired
    private UserRepository repository;

    private User userJohn;
    private User userTom;

    @BeforeAll
    void init() {

        userJohn = User.builder()
                .age(30)
                .email("john@doe.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        userTom = User.builder()
                .firstName("Tom")
                .lastName("Doe")
                .email("john@doe.com")
                .age(25)
                .build();

        repository.saveAll(Arrays.asList(userJohn, userTom));
    }

    @Test
    void givenLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec =
                new UserSpecification(new SearchCriteria("lastName", ":", "Doe"));

        List<User> results = repository.findAll(spec);

        assertTrue(results.contains(userJohn));
        assertTrue(results.contains(userTom));
    }

}
