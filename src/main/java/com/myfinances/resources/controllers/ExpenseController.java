package com.myfinances.resources.controllers;

import com.myfinances.model.entities.Expense;
import com.myfinances.model.entities.Tag;
import com.myfinances.resources.model.request.ExpenseRequest;
import com.myfinances.resources.model.request.TagRequest;
import com.myfinances.resources.model.response.ExpenseResponse;
import com.myfinances.resources.model.response.TagResponse;
import com.myfinances.services.ExpenseService;
import com.myfinances.shared.ExpenseDTO;
import com.myfinances.shared.TagDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    ExpenseService service;

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> findAll() {

        List<ExpenseDTO> dtos = service.findAll();


        List<ExpenseResponse> responses = new ArrayList<>();
//                dtos.stream()
//                .map(dto -> new ModelMapper().map(dto,ExpenseResponse.class))
//                .collect(Collectors.toList());

        for (int i = 0; i < dtos.size(); i++) {
            responses.add(getExpenseResponse(dtos.get(i)));

        }
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExpenseResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(getExpenseResponse(service.findById(id)));
    }


    @PostMapping
    public ResponseEntity<ExpenseResponse> insert(@RequestBody ExpenseRequest request) {
        ModelMapper mapper = new ModelMapper();
        ExpenseDTO dto = getExpenseDTO(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(getExpenseResponse(service.insert(dto)));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ExpenseResponse> update(@RequestBody ExpenseRequest request, @PathVariable Long id) {
        ExpenseDTO dto = getExpenseDTO(request);
        dto.setId(id);
        return ResponseEntity.ok().body(getExpenseResponse(service.update(dto)));
    }

private ExpenseDTO getExpenseDTO(ExpenseRequest request) {
    ModelMapper mapper = new ModelMapper();
    ExpenseDTO dto = mapper.map(request,ExpenseDTO.class);

    for (TagRequest tag: request.getTags()) {
        dto.getTags().add(mapper.map(tag, Tag.class));
    }
    return dto;
}

    private ExpenseResponse getExpenseResponse(ExpenseDTO dto) {
        ModelMapper mapper = new ModelMapper();
        ExpenseResponse response = mapper.map(dto,ExpenseResponse.class);
        for (Tag tag: dto.getTags()) {
            response.getTags().add(mapper.map(tag, TagResponse.class));
        }
        return response;
    }

}
