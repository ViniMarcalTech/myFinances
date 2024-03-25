package com.myfinances.myfinances.services;

import com.myfinances.myfinances.model.entities.User;
import com.myfinances.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.myfinances.repositories.ExpenseRepository;
import com.myfinances.myfinances.repositories.IncomeRepository;
import com.myfinances.myfinances.repositories.UserRepository;
import com.myfinances.myfinances.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<UserDTO> findAll() {

        List<User> users = repository.findAll();
        return users.stream()
                .map(user -> new ModelMapper().map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {

        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuario com o ID: " + id + " Não encontrado");
        }

        return new ModelMapper().map(user.get(), UserDTO.class);
    }


    public UserDTO insert(UserDTO dto) {

        dto.setId(null);
        User user = repository.save(new ModelMapper().map(dto, User.class));
        dto.setId(user.getId());
        return dto;

    }

    public void delete(Long id) {

        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Usuário com id: " + id + " Não encontrado");
        }

        expenseRepository.deleteByUser(id);
        incomeRepository.deleteByUser(id);
        repository.deleteById(id);

    }

    public UserDTO update(UserDTO dto) {

        if (repository.findById(dto.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Usuario não existe");
        }

        repository.save(new ModelMapper().map(dto, User.class));
        return dto;

    }


}
