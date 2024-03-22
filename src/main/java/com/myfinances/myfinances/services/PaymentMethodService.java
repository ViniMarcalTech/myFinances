package com.myfinances.myfinances.services;

import com.myfinances.myfinances.model.entities.PaymentMethod;
import com.myfinances.myfinances.model.exception.ResourceNotFoundException;
import com.myfinances.myfinances.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    @Autowired
    PaymentMethodRepository repository;

    public List<PaymentMethod> findAll() {

        return repository.findAll();
    }

    public PaymentMethod findById(Long id) {
        Optional<PaymentMethod> obj = repository.findById(id);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException("PaymentMethod com o id: " + id + " Não encontrado");
        }
        PaymentMethod payment = obj.get();
        return payment;
    }


    public PaymentMethod insert(PaymentMethod payment) {

        return repository.save(payment);

    }


    public void delete(Long id) {
        if (repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("PaymentMethod com id: "+id+" Não encontrado");
        }
        repository.deleteById(id);

    }

    public PaymentMethod update(PaymentMethod payment) {
        if (repository.findById(payment.getId()).isEmpty()) {
            throw new ResourceNotFoundException("PaymentMethod com id: "+payment.getId()+" Não encontrado");
        }

        return repository.save(payment);
    }


}
