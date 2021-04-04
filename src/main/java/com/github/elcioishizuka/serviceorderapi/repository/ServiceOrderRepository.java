package com.github.elcioishizuka.serviceorderapi.repository;

import com.github.elcioishizuka.serviceorderapi.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    Optional<ServiceOrder> findById(Long id);

}
