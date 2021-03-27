package com.github.elcioishizuka.serviceorderapi.repository;

import com.github.elcioishizuka.serviceorderapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);
    Optional<Customer> findById(Long id);
    List<Customer> findByNameContaining(String searchFor);

}
