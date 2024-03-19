package com.myfinances.myfinances.resources.controllers;

import com.myfinances.myfinances.entities.PaymentMethod;
import com.myfinances.myfinances.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/paymentmethod")
public class PaymentMethodController {

    @Autowired
    PaymentMethodService service;

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> findAll() {
        List<PaymentMethod> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentMethod> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> insert(@RequestBody PaymentMethod payment) {

        service.insert(payment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(payment.getId()).toUri();

        return ResponseEntity.created(uri).body(payment);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PaymentMethod> update(@PathVariable Long id, @RequestBody PaymentMethod payment) {
        payment.setId(id);
        payment = service.update(payment);
        return ResponseEntity.ok().body(payment);
    }

}
