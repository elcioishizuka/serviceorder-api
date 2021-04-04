package com.github.elcioishizuka.serviceorder.api.controller;

import com.github.elcioishizuka.serviceorder.domain.model.Customer;
import com.github.elcioishizuka.serviceorder.domain.exception.CustomerEmailAlreadyRegisteredException;
import com.github.elcioishizuka.serviceorder.domain.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorder.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class CustomerController {

    @Autowired
    CustomerService customerService;

//    @Autowired
//    CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> listAllClients(){
        return customerService.listAll();
    }

//    Code for training purpose
//    @GetMapping("/{name}")
//    public List<Customer> listByName(@PathVariable String name){
//        return customerService.listByName(name);
//    }

//    Code for training purpose
//    @GetMapping("/{searchFor}")
//    public List<Customer> listByNameContaining(@PathVariable String searchFor){
//        return customerService.listByNameContaining(searchFor);
//    }


//    @GetMapping("/{id}")
//    public Customer listById(@PathVariable Long id){
//        return customerService.listById(id);
//    }

    @GetMapping("/{id}")
    public Customer listById(@PathVariable Long id) throws CustomerNotFoundException {
        return customerService.listById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@Valid @RequestBody Customer customer) throws CustomerEmailAlreadyRegisteredException {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer modifyCustomer(@Valid @PathVariable Long id, @RequestBody Customer customer) throws CustomerNotFoundException {
        return customerService.modifyCustomer(id, customer);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Customer> modifyCustomer(@Valid @PathVariable Long id, @RequestBody Customer customer){
//        if(!customerRepository.existsById(id)){
//            return ResponseEntity.notFound().build();
//        }
//        customer.setId(id);
//        customer = customerRepository.save(customer);
//
//        return ResponseEntity.ok(customer);
//    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        customerService.deleteCustomer(id);
    }


}
