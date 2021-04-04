package com.github.elcioishizuka.serviceorderapi.controller;

import com.github.elcioishizuka.serviceorderapi.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorderapi.model.ServiceOrder;
import com.github.elcioishizuka.serviceorderapi.service.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/service-orders")
public class ServiceOrderController {

    @Autowired
    ServiceOrderService serviceOrderService;

    @GetMapping
    public List<ServiceOrder> listAllServiceOrders(){
        return serviceOrderService.listAllServiceOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrder createServiceOrder(@Valid @RequestBody ServiceOrder serviceOrder) throws CustomerNotFoundException {
        return serviceOrderService.createServiceOrder(serviceOrder);
    }
}
