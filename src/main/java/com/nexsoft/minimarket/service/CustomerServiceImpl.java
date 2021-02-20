package com.nexsoft.minimarket.service;

import com.nexsoft.minimarket.model.Customer;
import com.nexsoft.minimarket.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findByName(String nama) {
        return customerRepository.findByName(nama);
    }

    @Override
    public void save(Customer customer) {
        UUID customerId = UUID.randomUUID();
        customer.setId(customerId.toString());

        synchronized (this) {
            customerRepository.insert(customer);
        }
    }

    @Override
    public Customer update(Customer customer, String id) {
        Customer currentCustomer = customerRepository.findById(id);

        currentCustomer.setNama(customer.getNama());
        currentCustomer.setAlamat(customer.getAlamat());
        currentCustomer.setTelepon(customer.getTelepon());

        synchronized (this) {
            customerRepository.update(customer);
        }

        return  currentCustomer;
    }

    @Override
    public void deleteById(String id) {
        synchronized (this) {
            customerRepository.delete(id);
        }
    }

    @Override
    public boolean isCustomerExist(Customer customer) {
        return customerRepository.findByNameSpecific(customer.getNama()) != null;
    }

    @Override
    public boolean emptyValidation(Customer customer) {
        if(customer.getNama().isBlank())
        {
            return false;
        }
        else if(customer.getAlamat().isBlank())
        {
            return false;
        }
        else if(customer.getTelepon().isBlank())
        {
            return false;
        }

        return true;
    }
}
