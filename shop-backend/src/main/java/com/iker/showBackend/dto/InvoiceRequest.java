package com.iker.showBackend.dto;

import jakarta.validation.constraints.NotBlank;

public class InvoiceRequest {

    @NotBlank
    private String userAddress;
    
    private double totalPrice;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
