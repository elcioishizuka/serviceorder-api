package com.github.elcioishizuka.serviceorderapi.service;

import com.github.elcioishizuka.serviceorderapi.model.Customer;
import com.github.elcioishizuka.serviceorderapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> listAll(){
        return customerRepository.findAll();
    }




}
