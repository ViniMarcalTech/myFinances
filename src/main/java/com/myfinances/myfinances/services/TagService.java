package com.myfinances.myfinances.services;

import com.myfinances.myfinances.model.entities.Tag;
import com.myfinances.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.myfinances.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    TagRepository repository;


    public List<Tag> findAll() {

        return repository.findAll();
    }

    public Tag findById(Long id) {
        Optional<Tag> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("Tag com o id: " + id + " Não encontrado");
        }
        Tag tag = obj.get();
        return tag;
    }


    public Tag insert(Tag tag) {

        return repository.save(tag);
    }


    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Tag com o id: " + id + " Não encontrado");
        }
        repository.deleteById(id);

    }

    public Tag update(Tag tag) {
        if (repository.findById(tag.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Tag com id: " + tag.getId() + " Não encontrada");
        }
        return repository.save(tag);
    }


}
