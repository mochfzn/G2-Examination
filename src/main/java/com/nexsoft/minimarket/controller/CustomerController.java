package com.nexsoft.minimarket.controller;

import com.nexsoft.minimarket.model.Customer;
import com.nexsoft.minimarket.service.CustomerService;
import com.nexsoft.minimarket.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/market")
public class CustomerController {
    public static final Logger logger = LoggerFactory.getLogger(BarangController.class);

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/")
    public ResponseEntity<List<Customer>> listAllCustomer() {
        List<Customer> customerList = customerService.findAll();

        if (customerList.isEmpty()) {
            return new ResponseEntity<>(customerList, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") String id) {
        logger.info("Fetching customer with id {}", id);

        Customer customer = customerService.findById(id);

        if(customer == null) {
            logger.error("Customer with id {} not found", id);
            return new ResponseEntity<>(new CustomErrorType("Customer with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customer/nama/{nama}")
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable("nama") String nama) {
        List<Customer> customerList = customerService.findByName(nama);

        if (customerList.isEmpty()) {
            return new ResponseEntity<>(customerList, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping("/customer/")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        logger.info("Creating customer : {}", customer);

        if(!customerService.emptyValidation(customer))
        {
            logger.error("Unable to create. There is empty field in customer object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in customer object."), HttpStatus.FORBIDDEN);
        }
        else if(customerService.isCustomerExist(customer))
        {
            logger.error("Unable to create. A customer with id {} already exist", customer.getId());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Customer with id " + customer.getId() + " already exist."), HttpStatus.CONFLICT);
        }

        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
        logger.info("Updating customer with id {}", id);

        Customer customerFound = customerService.findById(id);

        if(!customerService.emptyValidation(customer))
        {
            logger.error("Unable to create. There is empty field in customer object.");
            return new ResponseEntity<>(new CustomErrorType("Unable to create. There is empty field in customer object."), HttpStatus.FORBIDDEN);
        }
        else if(customerFound == null)
        {
            logger.error("Unable to update. Customer with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Customer with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        Customer currentCustomer = customerService.update(customer, id);
        return new ResponseEntity<>(currentCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") String id) {
        logger.info("Fetching & Deleting customer with id {}", id);

        Customer customer = customerService.findById(id);

        if(customer == null) {
            logger.error("Unable to delete. Customer with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Customer with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        customerService.deleteById(id);
        return new ResponseEntity<>("Customer with id" + id + "has been deleted successfully", HttpStatus.OK);
    }
}
