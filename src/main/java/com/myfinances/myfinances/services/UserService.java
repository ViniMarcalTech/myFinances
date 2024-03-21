package com.myfinances.myfinances.services;

import com.myfinances.myfinances.entities.User;
import com.myfinances.myfinances.repositories.ExpenseRepository;
import com.myfinances.myfinances.repositories.IncomeRepository;
import com.myfinances.myfinances.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    @Autowired
//    ExpenseService expenseService;
    ExpenseRepository expenseRepository;
    @Autowired
//    IncomeService incomeService;
    IncomeRepository incomeRepository;

    public List<User> findAll() {

        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new IllegalArgumentException("Usuario com o ID: " + id + " Não encontrado");
        }
        User user = obj.get();
        return user;
    }


    public User insert(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
//       User  user = this.findById(id);
        expenseRepository.deleteByUser(id);
        incomeRepository.deleteByUser(id);
        repository.deleteById(id);

    }

    public User update(User user) {
        if (repository.findById(user.getId()).get() == null) {
            throw new IllegalArgumentException("Usuario não existe");
        }
        ;
        return repository.save(user);
    }


}
