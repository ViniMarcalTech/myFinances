package com.myfinances.myfinances.services;

import com.myfinances.myfinances.model.entities.Tag;
import com.myfinances.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.myfinances.repositories.TagRepository;
import com.myfinances.myfinances.shared.TagDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {
    @Autowired
    TagRepository repository;


    public List<TagDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(tag -> new ModelMapper().map(tag,TagDTO.class))
                .collect(Collectors.toList());
    }

    public TagDTO findById(Long id) {
        Optional<Tag> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("Tag com o id: " + id + " Não encontrado");
        }
        return new ModelMapper().map(obj.get(),TagDTO.class);
    }


    public TagDTO insert(TagDTO dto) {
        dto.setId(null);
        Tag tag = repository.save(new ModelMapper().map(dto,Tag.class));
        dto.setId(tag.getId());
        return dto;
    }


    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Tag com o id: " + id + " Não encontrado");
        }
        repository.deleteById(id);

    }

    public TagDTO update(TagDTO dto) {
        if (repository.findById(dto.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Tag com id: " + dto.getId() + " Não encontrada");
        }
        repository.save(new ModelMapper().map(dto,Tag.class));
        return dto;
    }


}
