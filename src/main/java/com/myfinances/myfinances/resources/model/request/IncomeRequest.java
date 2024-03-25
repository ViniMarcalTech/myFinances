package com.myfinances.myfinances.resources.model.request;

import com.myfinances.myfinances.model.entities.Category;
import com.myfinances.myfinances.model.entities.User;

import java.time.Instant;

public class IncomeRequest {

    private UserRequest user;
    private CategoryRequest category;
    private Double amount;
    private Instant date;

    public IncomeRequest() {
    }

    public IncomeRequest(UserRequest user, CategoryRequest category, Double amount, Instant date) {
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.date = date;
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
