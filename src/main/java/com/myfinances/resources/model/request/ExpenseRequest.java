package com.myfinances.resources.model.request;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRequest {


    private Long id;
    private UserRequest user;
    private CategoryRequest category;
    private PaymentMethodRequest paymentMethod;
    private List<TagRequest> tags = new ArrayList<>();
    private Double amount;
    private Instant date;

    public ExpenseRequest() {
    }

    public ExpenseRequest(Long id, UserRequest user, CategoryRequest category, PaymentMethodRequest paymentMethod, Double amount, Instant date) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TagRequest> getTags() {
        return tags;
    }

    public UserRequest getUser() {
        return user;
    }

    public void setUser(UserRequest user) {
        this.user = user;
    }

    public CategoryRequest getCategory() {
        return category;
    }

    public void setCategory(CategoryRequest category) {
        this.category = category;
    }

    public PaymentMethodRequest getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodRequest paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
