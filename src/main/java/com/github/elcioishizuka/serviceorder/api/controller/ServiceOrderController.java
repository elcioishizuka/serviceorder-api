package com.github.elcioishizuka.serviceorder.api.controller;

import com.github.elcioishizuka.serviceorder.api.dto.ServiceOrderDto;
import com.github.elcioishizuka.serviceorder.domain.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorder.domain.model.ServiceOrder;
import com.github.elcioishizuka.serviceorder.domain.exception.ServiceOrderNotFoundException;
import com.github.elcioishizuka.serviceorder.domain.service.ServiceOrderService;
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
    public List<ServiceOrderDto> listAllServiceOrders(){
        return serviceOrderService.listAllServiceOrders();
    }

    @GetMapping("/{serviceOrderId}")
    public ServiceOrderDto findById(@PathVariable Long serviceOrderId) throws ServiceOrderNotFoundException {
        return serviceOrderService.listById(serviceOrderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrderDto createServiceOrder(@Valid @RequestBody ServiceOrder serviceOrder) throws CustomerNotFoundException {
        return serviceOrderService.createServiceOrder(serviceOrder);
    }

    // TODO include DeleteMapping
}
