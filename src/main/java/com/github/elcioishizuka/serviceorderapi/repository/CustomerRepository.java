package com.github.elcioishizuka.serviceorderapi.repository;

import com.github.elcioishizuka.serviceorderapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
