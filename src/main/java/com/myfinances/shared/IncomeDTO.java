package com.myfinances.shared;

import com.myfinances.model.entities.Category;
import com.myfinances.model.entities.User;

import java.time.Instant;

public class IncomeDTO {
    private Long id;
    private User user;
    private Category category;
    private Double amount;
    private Instant date;

    public IncomeDTO() {
    }

    public IncomeDTO(Long id, User user, Category category, Double amount, Instant date) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
