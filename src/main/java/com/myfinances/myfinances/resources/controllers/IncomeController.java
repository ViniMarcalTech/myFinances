package com.myfinances.myfinances.resources.controllers;

import com.myfinances.myfinances.model.entities.Income;
import com.myfinances.myfinances.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    IncomeService service;

    @GetMapping
    public ResponseEntity<List<Income>> findAll() {
        List<Income> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Income> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }


    @PostMapping
    public ResponseEntity<Income> insert(@RequestBody Income income) {

        service.insert(income);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(income.getId()).toUri();
        return ResponseEntity.created(uri).body(income);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Income> update(@RequestBody Income income, @PathVariable Long id) {
        income.setId(id);
        income = service.update(income);
        return ResponseEntity.ok().body(income);
    }

}
