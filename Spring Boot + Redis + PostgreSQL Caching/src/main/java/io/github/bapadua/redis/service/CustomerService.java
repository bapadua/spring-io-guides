package io.github.bapadua.redis.service;

import io.github.bapadua.redis.domain.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    Customer add(Customer customer);

    Customer update(Customer customer);

    void delete(Long id);

    Customer getById(Long id);
}
