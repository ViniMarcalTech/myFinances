package com.myfinances.resources.model.response;

import com.myfinances.model.entities.Category;
import com.myfinances.model.entities.PaymentMethod;
import com.myfinances.model.entities.Tag;
import com.myfinances.model.entities.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ExpenseResponse {

    private Long id;
    private UserResponse user;
    private CategoryResponse category;
    private PaymentMethodResponse paymentMethod;
    private List<TagResponse> tags = new ArrayList<>();
    private Double amount;
    private Instant date;

    public ExpenseResponse() {
    }

    public ExpenseResponse(Long id, UserResponse user, CategoryResponse category, PaymentMethodResponse paymentMethod, Double amount, Instant date) {
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

    public List<TagResponse> getTags() {
        return tags;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public PaymentMethodResponse getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodResponse paymentMethod) {
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
