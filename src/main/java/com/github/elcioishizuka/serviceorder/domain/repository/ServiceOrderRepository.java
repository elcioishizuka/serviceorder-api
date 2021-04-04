package com.github.elcioishizuka.serviceorder.domain.repository;

import com.github.elcioishizuka.serviceorder.domain.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    Optional<ServiceOrder> findById(Long id);

}
