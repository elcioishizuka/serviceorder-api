package com.github.elcioishizuka.serviceorderapi.service;

import com.github.elcioishizuka.serviceorderapi.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorderapi.model.ServiceOrder;
import com.github.elcioishizuka.serviceorderapi.model.ServiceOrderStatus;
import com.github.elcioishizuka.serviceorderapi.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceOrderService {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @Autowired
    CustomerService customerService;

    public List<ServiceOrder> listAllServiceOrders(){
        return serviceOrderRepository.findAll();
    }

    public ServiceOrder createServiceOrder(ServiceOrder serviceOrder) throws CustomerNotFoundException {
        customerService.verifyIfExists(serviceOrder.getCustomer().getId());

        serviceOrder.setStatus(ServiceOrderStatus.OPEN);
        serviceOrder.setOpenDate(LocalDateTime.now());

        return serviceOrderRepository.save(serviceOrder);
    }

}
