package com.github.elcioishizuka.serviceorderapi.repository;

import com.github.elcioishizuka.serviceorderapi.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {


}
