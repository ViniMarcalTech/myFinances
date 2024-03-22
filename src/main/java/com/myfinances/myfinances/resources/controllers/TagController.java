package com.myfinances.myfinances.resources.controllers;

import com.myfinances.myfinances.model.entities.Tag;
import com.myfinances.myfinances.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    TagService service;

    @GetMapping
    public ResponseEntity<List<Tag>> findAll() {
        List<Tag> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tag> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Tag> insert(@RequestBody Tag tag) {

        service.insert(tag);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(tag.getId()).toUri();

        return ResponseEntity.created(uri).body(tag);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Tag> update(@PathVariable Long id, @RequestBody Tag tag) {
        tag.setId(id);
        tag = service.update(tag);
        return ResponseEntity.ok().body(tag);
    }

}
