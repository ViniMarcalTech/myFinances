package com.myfinances.resources.model.response;

public class PaymentMethodResponse {
    private Long id;
    private String paymentName;

    public PaymentMethodResponse() {
    }

    public PaymentMethodResponse(Long id, String paymentName) {
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


