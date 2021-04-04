package com.github.elcioishizuka.serviceorderapi.service;

import com.github.elcioishizuka.serviceorderapi.exception.CustomerEmailAlreadyRegisteredException;
import com.github.elcioishizuka.serviceorderapi.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorderapi.model.Customer;
import com.github.elcioishizuka.serviceorderapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    // Using ResponseEntity
//    public Customer listById(Long id) throws CustomerNotFoundException {
//        ResponseEntity<Customer> foundCustomer = Optional
//                .ofNullable(customerRepository.findById(id).orElse(null))
//                .map(found -> ResponseEntity.ok(found))
//                .orElseThrow(() -> new CustomerNotFoundException(id));
//        return foundCustomer.getBody();
//    }

    public List<Customer> listByNameContaining(String searchFor) {
        return customerRepository.findByNameContaining(searchFor);
    }

    public Customer addCustomer(Customer customer) throws CustomerEmailAlreadyRegisteredException {
        Customer customerWithThisEmailRegistered = verifyIfEmailAlreadyRegistered(customer.getEmail());
        if(customerWithThisEmailRegistered != null && !customerWithThisEmailRegistered.equals(customer)){
            throw new CustomerEmailAlreadyRegisteredException(customer.getEmail());
        }
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        verifyIfExists(id);
        customerRepository.deleteById(id);
    }

    public Customer modifyCustomer(Long id, Customer customer) throws CustomerNotFoundException {
        verifyIfExists(id);
        customer.setId(id);
        return customerRepository.save(customer);
    }

    public Customer verifyIfExists(Long id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    private Customer verifyIfEmailAlreadyRegistered(String email) {
        return customerRepository.findByEmail(email);
    }


}
