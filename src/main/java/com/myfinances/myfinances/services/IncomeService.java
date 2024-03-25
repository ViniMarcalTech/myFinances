package com.myfinances.myfinances.services;

import com.myfinances.myfinances.model.entities.Income;
import com.myfinances.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.myfinances.repositories.CategoryRepository;
import com.myfinances.myfinances.repositories.IncomeRepository;
import com.myfinances.myfinances.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Income> findAll() {

        return repository.findAll();
    }

    public Income findById(Long id) {
        Optional<Income> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("Income com o id: " + id + " Não encontrado");
        }
        Income income = obj.get();
        return income;
    }


    public Income insert(Income income) {
        income = validateIncome(income);
        return repository.save(income);
    }


    public void delete(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Income com id: " + id + " Não encontrada");
        }
        repository.deleteById(id);
    }

    public Income update(Income income) {
        if (repository.findById(income.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Income com id: " + income.getId() + " Não encontrada");
        }
        income = validateIncome(income);
        return repository.save(income);
    }


    private Income validateIncome(Income income) {
        income.setUser(userRepository.findById(income.getUser().getId()).get());
        income.setCategory(categoryRepository.findById(income.getCategory().getId()).get());
        return income;

    }


}
