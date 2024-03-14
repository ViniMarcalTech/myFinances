package com.myfinances.myfinances.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;


    @OneToMany(targetEntity = Expense.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    private List<Expense> expenses = new ArrayList<>();;

    @OneToMany(targetEntity = Income.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_fk", referencedColumnName = "id")
    private List<Income> incomes = new ArrayList<>();


    public User() {
    }

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
