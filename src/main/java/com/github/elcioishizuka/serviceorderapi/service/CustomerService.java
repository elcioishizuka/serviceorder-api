package com.github.elcioishizuka.serviceorderapi.service;

import com.github.elcioishizuka.serviceorderapi.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorderapi.model.Customer;
import com.github.elcioishizuka.serviceorderapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> listAll() {
        return customerRepository.findAll();
    }

    public List<Customer> listByName(String name) {
        return customerRepository.findByName(name);
    }

    public Customer listById(Long id) throws CustomerNotFoundException {
        Customer foundCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return foundCustomer;
    }

    public List<Customer> listByNameContaining(String searchFor) {
        return customerRepository.findByNameContaining(searchFor);
    }
}
