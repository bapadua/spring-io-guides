package io.github.bapadua.redis.repository;

import io.github.bapadua.redis.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
