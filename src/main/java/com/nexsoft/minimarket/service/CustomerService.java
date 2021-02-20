package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(String id);
    List<Customer> findByName(String nama);
    void save(Customer customer);
    Customer update(Customer customer, String id);
    void deleteById(String id);
    boolean isCustomerExist(Customer customer);

    boolean emptyValidation(Customer customer);
}
