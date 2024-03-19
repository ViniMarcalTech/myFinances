package com.myfinances.myfinances.services;

import com.myfinances.myfinances.entities.Income;
import com.myfinances.myfinances.repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository repository;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    public List<Income> findAll() {

        return repository.findAll();
    }

    public Income findById(Long id) {
        Optional<Income> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new NullPointerException("Income com o id: " + id + " Não encontrado");
        }
        Income income = obj.get();
        return income;
    }


    public Income insert(Income income, Long userID, Long categoryID) {
        income.setUser(userService.findById(userID));
        income.setCategory(categoryService.findById(categoryID));
        return repository.save(income);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Income update(Income income, Long userID, Long categoryID) {
        if (repository.findById(income.getId()).get() == null) {
            throw new IllegalArgumentException("Income não existe");
        }
        income.setUser(userService.findById(userID));
        income.setCategory(categoryService.findById(categoryID));
        return repository.save(income);
    }


}
