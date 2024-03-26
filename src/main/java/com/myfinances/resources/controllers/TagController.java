package com.myfinances.resources.controllers;

import com.myfinances.resources.model.request.TagRequest;
import com.myfinances.resources.model.response.TagResponse;
import com.myfinances.services.TagService;
import com.myfinances.shared.TagDTO;
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
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    TagService service;

    @GetMapping
    public ResponseEntity<List<TagResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll()
                .stream()
                .map(dto -> new ModelMapper().map(dto, TagResponse.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TagResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ModelMapper().map(service.findById(id), TagResponse.class));
    }

    @PostMapping
    public ResponseEntity<TagResponse> insert(@RequestBody TagRequest request) {

        ModelMapper mapper = new ModelMapper();
        TagDTO dto = service.insert(mapper.map(request, TagDTO.class));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(mapper.map(dto, TagResponse.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TagResponse> update(@PathVariable Long id, @RequestBody TagRequest request) {
        ModelMapper mapper = new ModelMapper();
        TagDTO dto = mapper.map(request, TagDTO.class);
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto), TagResponse.class));
    }

}
