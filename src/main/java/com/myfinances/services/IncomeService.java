package com.myfinances.services;

import com.myfinances.model.entities.Income;
import com.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.repositories.CategoryRepository;
import com.myfinances.repositories.IncomeRepository;
import com.myfinances.repositories.UserRepository;
import com.myfinances.shared.IncomeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<IncomeDTO> findAll() {
        return repository.findAll().stream()
                .map(income -> new ModelMapper().map(income, IncomeDTO.class))
                .collect(Collectors.toList());
    }

    public IncomeDTO findById(Long id) {
        Optional<Income> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("Income com o id: " + id + " Não encontrado");
        }
        return new ModelMapper().map(obj.get(),IncomeDTO.class);
    }


    public IncomeDTO insert(IncomeDTO dto) {
        Income income = validateIncome(dto);
        return new ModelMapper().map(repository.save(income),IncomeDTO.class);
    }


    public void delete(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Income com id: " + id + " Não encontrada");
        }
        repository.deleteById(id);
    }

    public IncomeDTO update(IncomeDTO dto) {
        if (repository.findById(dto.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Income com id: " + dto.getId() + " Não encontrada");
        }
        Income income = validateIncome(dto);
        return new ModelMapper().map(repository.save(income),IncomeDTO.class);
    }


    private Income validateIncome(IncomeDTO dto) {
        dto.setUser(userRepository.findById(dto.getUser().getId()).get());
        dto.setCategory(categoryRepository.findById(dto.getCategory().getId()).get());
        return new  ModelMapper().map(dto, Income.class);
    }


}
