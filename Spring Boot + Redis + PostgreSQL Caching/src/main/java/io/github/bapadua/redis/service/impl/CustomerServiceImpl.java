package io.github.bapadua.redis.service.impl;

import io.github.bapadua.redis.domain.entity.Customer;
import io.github.bapadua.redis.repository.CustomerRepository;
import io.github.bapadua.redis.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "customerCache")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Cacheable(cacheNames = "customers")
    @Override
    public List<Customer> getAll() {
        waitSomeTime();
        return customerRepository.findAll();
    }

    @CacheEvict(cacheNames = "customers", allEntries = true)
    @Override
    public Customer add(final Customer customer) {
        return customerRepository.save(customer);
    }

    @CacheEvict(cacheNames = "customers", allEntries = true)
    @Override
    public Customer update(final Customer customer) {
        var data = customerRepository.findById(customer.getId()).orElseThrow(() -> new RuntimeException("user does not exists"));
        data.setName(customer.getName());
        data.setContactName(customer.getContactName());
        data.setCountry(customer.getCountry());
        data.setPostalCode(customer.getPostalCode());
        data.setCity(customer.getCity());
        data.setAddress(customer.getAddress());
        return customerRepository.save(data);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "customer", key = "#id"),
            @CacheEvict(cacheNames = "customers", allEntries = true)
    })
    @Override
    public void delete(final Long id) {
        this.customerRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "customer", key = "#id", unless = "#result == null")
    @Override
    public Customer getById(final Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("user does not exists"));
    }

    private void waitSomeTime() {
        System.out.println("Long Wait Begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Long Wait End");
    }

}
