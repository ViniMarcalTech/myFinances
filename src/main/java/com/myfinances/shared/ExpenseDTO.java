package com.myfinances.shared;

import com.myfinances.model.entities.Category;
import com.myfinances.model.entities.PaymentMethod;
import com.myfinances.model.entities.Tag;
import com.myfinances.model.entities.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDTO {
    private Long id;
    private User user;
    private Category category;
    private PaymentMethod paymentMethod;
    private List<Tag> tags = new ArrayList<>();
    private Double amount;
    private Instant date;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Long id, User user, Category category, PaymentMethod paymentMethod, Double amount, Instant date) {
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

    public List<Tag> getTags() {
        return tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
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
