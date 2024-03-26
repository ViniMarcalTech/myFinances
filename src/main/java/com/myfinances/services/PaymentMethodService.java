package com.myfinances.services;

import com.myfinances.model.entities.PaymentMethod;
import com.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.repositories.PaymentMethodRepository;
import com.myfinances.shared.PaymentMethodDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService {
    @Autowired
    PaymentMethodRepository repository;

    public List<PaymentMethodDTO> findAll() {

        return repository.findAll().stream().map(payment -> new ModelMapper()
                .map(payment, PaymentMethodDTO.class))
                .collect(Collectors.toList());
    }

    public PaymentMethodDTO findById(Long id) {
        Optional<PaymentMethod> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("PaymentMethod com o id: " + id + " Não encontrado");
        }
        return new ModelMapper().map(obj.get(), PaymentMethodDTO.class);
    }


    public PaymentMethodDTO insert(PaymentMethodDTO dto) {
        dto.setId(null);
        PaymentMethod payment = repository.save(new ModelMapper().map(dto, PaymentMethod.class));
        dto.setId(payment.getId());
        return dto;

    }


    public void delete(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("PaymentMethod com id: "+id+" Não encontrado");
        }
        repository.deleteById(id);

    }

    public PaymentMethodDTO update(PaymentMethodDTO dto) {
        if (repository.findById(dto.getId()).isEmpty()) {
            throw new ResourceNotFoundException("PaymentMethod com id: "+dto.getId()+" Não encontrado");
        }
        repository.save(new ModelMapper().map(dto,PaymentMethod.class));
        return dto;
    }


}
