package com.myfinances.myfinances.services;

import com.myfinances.myfinances.entities.PaymentMethod;
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
            throw new RuntimeException("PaymentMethod com o id: " + id + " Não encontrado");
        }
        PaymentMethod payment = obj.get();
        return payment;
    }


    public PaymentMethod insert(PaymentMethod payment) {
        if (payment == null) {
            throw new RuntimeException("PaymentMethod invalido");
        }
        return repository.save(payment);

    }

//    public void  insertAll(List<PaymentMethod> payments){
//
//        if (payments == null){
//            throw new RuntimeException("Lista de pagamentos vazia");
//        }
//        repository.saveAll(payments);
//    }

    public void delete(Long id) {
        repository.deleteById(id);

    }

    public PaymentMethod update(PaymentMethod payment) {
        if (repository.findById(payment.getId()).get() == null) {
            throw new IllegalArgumentException("PaymentMethod não existe");
        }
        ;
        return repository.save(payment);
    }


}
