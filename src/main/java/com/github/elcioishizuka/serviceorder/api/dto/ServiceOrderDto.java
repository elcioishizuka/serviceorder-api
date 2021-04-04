package com.github.elcioishizuka.serviceorder.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.elcioishizuka.serviceorder.domain.model.ServiceOrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceOrderDto {

    private Long id;
    private String customerName;
    private String description;
    private BigDecimal price;
    private ServiceOrderStatus status;
    private OffsetDateTime openDate;
    private OffsetDateTime closeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ServiceOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceOrderStatus status) {
        this.status = status;
    }

    public OffsetDateTime getOpenDate() {
        return openDate;
    }

    public void setOpenDate(OffsetDateTime openDate) {
        this.openDate = openDate;
    }

    public OffsetDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(OffsetDateTime closeDate) {
        this.closeDate = closeDate;
    }
}
