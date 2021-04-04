package com.github.elcioishizuka.serviceorder.domain.service;

import com.github.elcioishizuka.serviceorder.domain.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorder.domain.model.ServiceOrder;
import com.github.elcioishizuka.serviceorder.domain.model.ServiceOrderStatus;
import com.github.elcioishizuka.serviceorder.domain.repository.ServiceOrderRepository;
import com.github.elcioishizuka.serviceorder.domain.exception.ServiceOrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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
        serviceOrder.setOpenDate(OffsetDateTime.now());

        return serviceOrderRepository.save(serviceOrder);
    }

    public ServiceOrder listById(Long serviceOrderId) throws ServiceOrderNotFoundException {
        ServiceOrder foundServiceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new ServiceOrderNotFoundException(serviceOrderId));

        return foundServiceOrder;
    }
}
