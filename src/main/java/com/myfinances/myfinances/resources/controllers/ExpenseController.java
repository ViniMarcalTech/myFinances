package com.myfinances.myfinances.resources.controllers;

import com.myfinances.myfinances.model.entities.Expense;
import com.myfinances.myfinances.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    ExpenseService service;

    @GetMapping
    public ResponseEntity<List<Expense>> findAll() {
        List<Expense> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Expense> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }


    @PostMapping
    public ResponseEntity<Expense> insert(@RequestBody Expense expense) {

        service.insert(expense);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(expense.getId()).toUri();
        return ResponseEntity.created(uri).body(expense);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Expense> update(@RequestBody Expense expense, @PathVariable Long id) {

        expense.setId(id);
        expense = service.update(expense);
        return ResponseEntity.ok().body(expense);
    }

}
