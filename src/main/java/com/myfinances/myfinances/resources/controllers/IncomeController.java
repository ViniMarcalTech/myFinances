package com.myfinances.myfinances.resources.controllers;

import com.myfinances.myfinances.entities.Category;
import com.myfinances.myfinances.entities.Income;
import com.myfinances.myfinances.entities.User;
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


    @PostMapping(value = "/{userID}/{categoryID}")
    public ResponseEntity<Income> insert(@RequestBody Income income, @PathVariable Long userID, @PathVariable Long categoryID) {

        service.insert(income,userID,categoryID);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(income.getId()).toUri();
        return ResponseEntity.created(uri).body(income);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{userID}/{categoryID}")
    public ResponseEntity<Income> update(@RequestBody Income income,@PathVariable Long userID, @PathVariable Long categoryID) {

        income = service.update(income,userID,categoryID);
        return ResponseEntity.ok().body(income);
    }

}
