package com.myfinances.myfinances.shared;

import com.myfinances.myfinances.model.entities.PaymentMethod;

import java.util.Objects;

public class PaymentMethodDTO {
    private Long id;
    private String paymentName;

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(Long id, String paymentName) {
        this.id = id;
        this.paymentName = paymentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

}
