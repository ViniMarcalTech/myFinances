package com.myfinances.myfinances.resources.controllers;

import com.myfinances.myfinances.model.entities.User;
import com.myfinances.myfinances.resources.model.UserRequest;
import com.myfinances.myfinances.resources.model.UserResponse;
import com.myfinances.myfinances.services.UserService;
import com.myfinances.myfinances.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserDTO> list = service.findAll();
        List<UserResponse> response = list.stream().map( dto -> new ModelMapper().map(dto, UserResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ModelMapper().map(service.findById(id), UserResponse.class));
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(@RequestBody UserRequest request) {

        ModelMapper mapper = new ModelMapper();
        UserDTO dto = mapper.map(request, UserDTO.class);
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(mapper.map(dto, UserResponse.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest request) {
        ModelMapper mapper = new ModelMapper();
        UserDTO dto = mapper.map(request,UserDTO.class);
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto),UserResponse.class));
    }

}
