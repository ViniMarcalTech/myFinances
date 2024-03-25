package com.myfinances.myfinances.resources.model.response;

import com.myfinances.myfinances.model.entities.Category;
import com.myfinances.myfinances.model.entities.User;

import java.time.Instant;

public class IncomeResponse {

    private Long id;
    private UserResponse user;
    private CategoryResponse category;
    private Double amount;
    private Instant date;

    public IncomeResponse() {
    }

    public IncomeResponse(Long id, UserResponse user, CategoryResponse category, Double amount, Instant date) {
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
