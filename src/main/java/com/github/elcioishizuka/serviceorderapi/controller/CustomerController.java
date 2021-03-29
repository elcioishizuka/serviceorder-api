package com.github.elcioishizuka.serviceorderapi.controller;

import com.github.elcioishizuka.serviceorderapi.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorderapi.model.Customer;
import com.github.elcioishizuka.serviceorderapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DefaultEditorKit;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class CustomerController {

    @Autowired
    CustomerService customerService;

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
    public Customer addCustomer(@Valid @RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer modifyCustomer(@Valid @PathVariable Long id, @RequestBody Customer customer) throws CustomerNotFoundException {
        return customerService.modifyCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        customerService.deleteCustomer(id);
    }


}
