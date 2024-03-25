package com.myfinances.myfinances.resources.controllers;

import com.myfinances.myfinances.model.entities.Category;
import com.myfinances.myfinances.resources.model.request.CategoryRequest;
import com.myfinances.myfinances.resources.model.response.CategoryResponse;
import com.myfinances.myfinances.resources.model.response.TagResponse;
import com.myfinances.myfinances.services.CategoryService;
import com.myfinances.myfinances.shared.CategoryDTO;
import com.myfinances.myfinances.shared.TagDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/category")
public class CategoryController {


    @Autowired
    CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream()
                .map(dto -> new ModelMapper().map(dto,CategoryResponse.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ModelMapper().map(service.findById(id), CategoryResponse.class));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> insert(@RequestBody CategoryRequest request) {

        ModelMapper mapper = new ModelMapper();
        CategoryDTO dto = service.insert(mapper.map(request, CategoryDTO.class));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(mapper.map(dto, CategoryResponse.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest request) {

        ModelMapper mapper = new ModelMapper();
        CategoryDTO dto = mapper.map(request, CategoryDTO.class);
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto), CategoryResponse.class));
    }

}
