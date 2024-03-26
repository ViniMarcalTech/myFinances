package com.myfinances.services;

import com.myfinances.model.entities.Category;
import com.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.repositories.CategoryRepository;
import com.myfinances.shared.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;


    public List<CategoryDTO> findAll() {

        return repository.findAll().stream()
                .map(category -> new ModelMapper().map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("Category com o id: " + id + " Não encontrado");
        }
        return new ModelMapper().map(obj.get(), CategoryDTO.class);
    }


    public CategoryDTO insert(CategoryDTO dto) {
        dto.setId(null);
        Category category = repository.save(new  ModelMapper().map(dto, Category.class));
        dto.setId(category.getId());
        return dto;
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Categoria com id: " +id+" Não existe");
        }

        repository.deleteById(id);

    }

    public CategoryDTO update(CategoryDTO dto) {
        if (repository.findById(dto.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Categoria com id: " +dto.getId()+" Não existe");
        }
        repository.save(new ModelMapper().map(dto, Category.class));
        return dto;
    }


}
