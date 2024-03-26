package com.myfinances.services;

import com.myfinances.model.entities.Expense;
import com.myfinances.model.entities.Tag;
import com.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.repositories.*;
import com.myfinances.shared.ExpenseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<ExpenseDTO> findAll() {
        List<ExpenseDTO> dtos = repository.findAll().stream()
                .map(expense -> getExpenseDto(expense))
                .collect(Collectors.toList());

        System.out.println("TESTE");

        return dtos;
    }

    public ExpenseDTO findById(Long id) {
        Optional<Expense> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("Income com o id: " + id + " Não encontrado");
        }
        ExpenseDTO dto = getExpenseDto(obj.get());
        System.out.println("TESTE");
        return dto;
    }


    public ExpenseDTO insert(ExpenseDTO dto) {
        Expense expense = validateExpense(dto);

        return getExpenseDto(repository.save(expense));
    }


    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Expense com id: " + id + " Não encontrada");
        }
        repository.deleteById(id);
    }

    public ExpenseDTO update(ExpenseDTO dto) {
        if (repository.findById(dto.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Expense com id: " + dto.getId() + " Não encontrada");
        }
        Expense expense = validateExpense(dto);

        return getExpenseDto(repository.save(expense));
    }


    private Expense validateExpense(ExpenseDTO dto) {

        Expense expense = new ModelMapper().map(dto, Expense.class);

        expense.setUser(userRepository.findById(dto.getUser().getId()).get());
        expense.setCategory(categoryRepository.findById(dto.getCategory().getId()).get());
        expense.setPaymentMethod(paymentMethodRepository.findById(dto.getPaymentMethod().getId()).get());


        for (int i = 0; i < dto.getTags().size(); i++) {
            expense.getTags().add(i, tagRepository.findById(dto.getTags().get(i).getId()).get());


        }

        return expense;
    }

    private ExpenseDTO getExpenseDto(Expense expense){

        ExpenseDTO dto = new ModelMapper().map(expense, ExpenseDTO.class);
        for (Tag tag: expense.getTags()) {
            dto.getTags().add(tag);
        }


        return  dto;
    }
}



