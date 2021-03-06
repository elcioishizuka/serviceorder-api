package com.github.elcioishizuka.serviceorder.domain.repository;

import com.github.elcioishizuka.serviceorder.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);
    Optional<Customer> findById(Long id);
    List<Customer> findByNameContaining(String searchFor);
    Customer findByEmail(String email);

}
