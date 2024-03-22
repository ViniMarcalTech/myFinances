package com.myfinances.myfinances.services;

import com.myfinances.myfinances.model.entities.Category;
import com.myfinances.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.myfinances.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;


    public List<Category> findAll() {

        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("Category com o id: " + id + " Não encontrado");
        }
        Category category = obj.get();
        return category;
    }


    public Category insert(Category category) {

        return repository.save(category);
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Categoria com id: " +id+" Não existe");
        }

        repository.deleteById(id);

    }

    public Category update(Category category) {
        if (repository.findById(category.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Categoria com id: " +category.getId()+" Não existe");
        }

        return repository.save(category);
    }


}
