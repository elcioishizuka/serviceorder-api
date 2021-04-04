package com.github.elcioishizuka.serviceorder.domain.service;

import com.github.elcioishizuka.serviceorder.api.dto.ServiceOrderDto;
import com.github.elcioishizuka.serviceorder.domain.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorder.domain.model.ServiceOrder;
import com.github.elcioishizuka.serviceorder.domain.model.ServiceOrderStatus;
import com.github.elcioishizuka.serviceorder.domain.repository.ServiceOrderRepository;
import com.github.elcioishizuka.serviceorder.domain.exception.ServiceOrderNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;


    public ServiceOrderDto createServiceOrder(ServiceOrder serviceOrder) throws CustomerNotFoundException {
        customerService.verifyIfExists(serviceOrder.getCustomer().getId());

        serviceOrder.setStatus(ServiceOrderStatus.OPEN);
        serviceOrder.setOpenDate(OffsetDateTime.now());

        return toDto(serviceOrderRepository.save(serviceOrder));
    }

    public ServiceOrderDto listById(Long serviceOrderId) throws ServiceOrderNotFoundException {
        ServiceOrder foundServiceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new ServiceOrderNotFoundException(serviceOrderId));

//        // Mapping manually the ServiceOrder to ServiceOrderDto without modelmapper or mapstruct
//        ServiceOrderDto foundServiceOrderDto = new ServiceOrderDto();
//        foundServiceOrderDto.setId(foundServiceOrder.getId());
//        foundServiceOrderDto.setCustomerName(foundServiceOrder.getCustomer().getName());
//        foundServiceOrderDto.setDescription(foundServiceOrder.getDescription());
//        foundServiceOrderDto.setPrice(foundServiceOrder.getPrice());
//        foundServiceOrderDto.setStatus(foundServiceOrder.getStatus());
//        foundServiceOrderDto.setOpenDate(foundServiceOrder.getOpenDate());
//        foundServiceOrderDto.setCloseDate(foundServiceOrder.getCloseDate());

        ServiceOrderDto foundServiceOrderDto = toDto(foundServiceOrder);

        return foundServiceOrderDto;

    }

    // Mapping using modelmapper
    private ServiceOrderDto toDto(ServiceOrder foundServiceOrder) {
        ServiceOrderDto foundServiceOrderDto = modelMapper.map(foundServiceOrder, ServiceOrderDto.class);
        return foundServiceOrderDto;
    }

    public List<ServiceOrderDto> listAllServiceOrders(){

        return serviceOrderRepository.findAll().stream()
                .map(serviceOrder -> toDto(serviceOrder))
                .collect(Collectors.toList());
    }

}
