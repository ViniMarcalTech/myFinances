package com.myfinances.myfinances.resources.controllers;

import com.myfinances.myfinances.model.entities.Income;
import com.myfinances.myfinances.resources.model.request.IncomeRequest;
import com.myfinances.myfinances.resources.model.response.IncomeResponse;
import com.myfinances.myfinances.services.IncomeService;
import com.myfinances.myfinances.shared.IncomeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    IncomeService service;

    @GetMapping
    public ResponseEntity<List<IncomeResponse>> findAll() {



        return ResponseEntity.ok().body( service.findAll().stream().map(dto -> new ModelMapper().map(dto,IncomeResponse.class)).collect(Collectors.toList()) );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IncomeResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ModelMapper().map(service.findById(id),IncomeResponse.class));
    }


    @PostMapping
    public ResponseEntity<IncomeResponse> insert(@RequestBody IncomeRequest request) {

        ModelMapper mapper = new ModelMapper();
        IncomeDTO dto = service.insert(mapper.map(request,IncomeDTO.class));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(dto,IncomeResponse.class));


    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<IncomeResponse> update(@RequestBody IncomeRequest request, @PathVariable Long id) {
        ModelMapper mapper = new ModelMapper();
        IncomeDTO dto = mapper.map(request, IncomeDTO.class);
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto),IncomeResponse.class));
    }

}
