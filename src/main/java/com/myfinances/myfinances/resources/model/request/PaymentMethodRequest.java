package com.myfinances.myfinances.resources.model.request;

public class PaymentMethodRequest {
    private Long id;
    private String paymentName;

    public PaymentMethodRequest() {
    }

    public PaymentMethodRequest(Long id, String paymentName) {
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
