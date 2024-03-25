package com.myfinances.myfinances.services;

import com.myfinances.myfinances.model.entities.Expense;
import com.myfinances.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.myfinances.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository repository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TagRepository tagRepository;
    @Autowired
    PaymentMethodRepository paymentMethodRepository;


    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Expense findById(Long id) {
        Optional<Expense> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("Income com o id: " + id + " Não encontrado");
        }
        Expense expense = obj.get();
        return expense;
    }


    public Expense insert(Expense expense) {
        expense = validateExpense(expense);
        return repository.save(expense);
    }


    public void delete(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Expense com id: "+id+" Não encontrada");
        }
        repository.deleteById(id);
    }

    public Expense update(Expense expense) {
        if (repository.findById(expense.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Expense com id: " + expense.getId() + " Não encontrada");
        }
        expense = validateExpense(expense);
        return repository.save(expense);
    }


    private Expense validateExpense(Expense expense) {

        expense.setUser(userRepository.findById(expense.getUser().getId()).get());
        expense.setCategory(categoryRepository.findById(expense.getCategory().getId()).get());
        expense.setPaymentMethod(paymentMethodRepository.findById(expense.getPaymentMethod().getId()).get());


        for (int i = 0; i < expense.getTags().size(); i++) {
            expense.getTags().set(i, tagRepository.findById(expense.getTags().get(i).getId()).get());
        }


        return expense;
    }


}
