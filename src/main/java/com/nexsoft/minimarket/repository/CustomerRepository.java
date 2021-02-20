package com.nexsoft.minimarket.repository;

import com.nexsoft.minimarket.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();
    Customer findById(String id);
    List<Customer> findByName(String nama);
    Customer findByNameSpecific(String nama);
    int insert(Customer customer);
    int update(Customer customer);
    int delete(String id);
}
