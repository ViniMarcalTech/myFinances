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


    public Income insert(Income income) {

        income = validateIncome(income);


        return repository.save(income);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Income update(Income income) {
        if (repository.findById(income.getId()).get() == null) {
            throw new IllegalArgumentException("Income não existe");
        }
        income = validateIncome(income);
        return repository.save(income);
    }


    private Income validateIncome(Income income) {
        income = validateCategory(income);
        income = validateUser(income);
        return income;

    }

    private Income validateUser(Income income) {
        if (income.getUser().getId() == null) {
            throw new IllegalArgumentException("Categoria não existe! ");
        } else {
            income.setUser(userService.findById(income.getUser().getId()));
        }
        return income;

    }

    private Income validateCategory(Income income) {
        if (income.getCategory().getId() == null) {
            throw new IllegalArgumentException("Categoria não existe! ");
        } else {
            income.setCategory(categoryService.findById(income.getCategory().getId()));
        }
        return income;
    }


}
