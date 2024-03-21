package com.myfinances.myfinances.services;

import com.myfinances.myfinances.entities.Expense;
import com.myfinances.myfinances.entities.User;
import com.myfinances.myfinances.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository repository;

    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    TagService tagService;
    @Autowired
    PaymentMethodService paymentService;


    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Expense findById(Long id) {
        Optional<Expense> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new NullPointerException("Income com o id: " + id + " Não encontrado");
        }
        Expense expense = obj.get();
        return expense;
    }


    public Expense insert(Expense expense) {

        expense = validateExpense(expense);


        return repository.save(expense);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Expense update(Expense expense) {
        if (repository.findById(expense.getId()).get() == null) {
            throw new IllegalArgumentException("Expense não existe");
        }
        expense = validateExpense(expense);
        return repository.save(expense);
    }


    private Expense validateExpense(Expense expense) {
        expense = validateUser(expense);
        expense = validateCategory(expense);
        expense = validatePayment(expense);
        expense = validateTags(expense);
        return expense;
    }

    private Expense validateTags(Expense expense) {


        for (int i = 0; i < expense.getTags().size(); i++) {
            if (expense.getTags().get(i).getId() == null) {
                throw new IllegalArgumentException("Tag invalida ");
            } else {
                expense.getTags().set(i, tagService.findById(expense.getTags().get(i).getId()));
            }
        }

        return expense;

    }

    private Expense validatePayment(Expense expense) {
        if (expense.getPaymentMethod().getId() == null) {
            throw new IllegalArgumentException("Metodo de pagamento invalido");
        } else {
            expense.setPaymentMethod(paymentService.findById(expense.getPaymentMethod().getId()));
        }
        return expense;
    }

    private Expense validateCategory(Expense expense) {
        if (expense.getCategory().getId() == null) {
            throw new IllegalArgumentException("Categoria invalido");
        } else {
            expense.setCategory(categoryService.findById(expense.getCategory().getId()));
        }
        return expense;
    }

    private Expense validateUser(Expense expense) {

        if (expense.getUser().getId() == null) {
            throw new IllegalArgumentException("Usuario Invalido! ");
        } else {
            expense.setUser(userService.findById(expense.getUser().getId()));
        }
        return expense;


    }


}
