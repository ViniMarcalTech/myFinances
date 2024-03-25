package com.myfinances.myfinances.resources.controllers;

import com.myfinances.myfinances.resources.model.request.PaymentMethodRequest;
import com.myfinances.myfinances.resources.model.response.PaymentMethodResponse;
import com.myfinances.myfinances.services.PaymentMethodService;
import com.myfinances.myfinances.shared.PaymentMethodDTO;
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
@RequestMapping("/api/paymentmethod")
public class PaymentMethodController {

    @Autowired
    PaymentMethodService service;

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponse>> findAll() {

        return ResponseEntity.ok().body(service.findAll()
                .stream()
                .map(dto -> new ModelMapper().map(dto, PaymentMethodResponse.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentMethodResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ModelMapper().map(service.findById(id), PaymentMethodResponse.class));
    }

    @PostMapping
    public ResponseEntity<PaymentMethodResponse> insert(@RequestBody PaymentMethodRequest request) {

        ModelMapper mapper = new ModelMapper();
        PaymentMethodDTO dto = service.insert(mapper.map(request,PaymentMethodDTO.class));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(mapper.map(dto, PaymentMethodResponse.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PaymentMethodResponse> update(@PathVariable Long id, @RequestBody PaymentMethodRequest request) {
        ModelMapper mapper = new ModelMapper();
        PaymentMethodDTO dto = mapper.map(request, PaymentMethodDTO.class);
        dto.setId(id);

        return ResponseEntity.ok().body(mapper.map(service.update(dto), PaymentMethodResponse.class));
    }

}
