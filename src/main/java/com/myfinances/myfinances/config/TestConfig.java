package com.myfinances.myfinances.config;

import com.myfinances.myfinances.entities.*;
import com.myfinances.myfinances.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    TagService tagRepository;
    @Autowired
    PaymentMethodService paymentMethodService;
    @Autowired
    IncomeService incomeRepository;
    @Autowired
    ExpenseService expenseService;
    @Autowired
    CategoryService categoryRepository;
    @Autowired
    UserService userService;


    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(null, "Marcos Vinicius", "vinicius@gmail.com", "9 9 9394 9299", "SenhaAbsurd4");
        User user2 = new User(null, "Maria Chica", "maria@gmail.com", "9 9 9299 9949", "Chaves123");
        User user3 = new User(null, "Maria Chica", "maria@gmail.com", "9 9 9299 9949", "Chaves123");

        userService.insert(user1);
        userService.insert(user2);
        userService.insert(user3);


        Category cat1 = new Category(null, "Alimentação", "Gastos com Alimentação");
        Category cat2 = new Category(null, "Combustivel", "Gastos com Combustiveis");
        Category cat3 = new Category(null, "Salario", "Dinheiro recebido");


        Tag tag1 = new Tag(null, "Restaurante da Tia");
        Tag tag2 = new Tag(null, "Gasolina");
        Tag tag3 = new Tag(null, "Restaurante do cleber");
        Tag tag4 = new Tag(null, "Não usei essa tag");

        PaymentMethod pay1 = new PaymentMethod(null, "Cartão de Credito");
        PaymentMethod pay2 = new PaymentMethod(null, "Dinheiro");

        Expense expense1 = new Expense(null, user1, cat2, pay1, 45.3, Instant.parse("2024-06-20T21:53:07Z"));
        Expense expense2 = new Expense(null, user2,cat1, pay2, 70.3, Instant.parse("2024-06-20T21:53:07Z"));
        Income income = new Income(null, user3, cat3, 1500.5, Instant.parse("2024-06-20T21:53:07Z"));
        expense1.getTags().addAll(Arrays.asList(tag1,tag2));
        expense2.getTags().add(tag2);

        categoryRepository.insertAll(Arrays.asList(cat1, cat2, cat3));
        paymentMethodService.insert(pay1);
        paymentMethodService.insert(pay2);
        tagRepository.insertAll(Arrays.asList(tag1, tag2, tag3, tag4));

//        expenseService.insert(expense1);
//        expenseService.insert(expense2);
//        incomeRepository.insert(income);

//        user1.getExpenses().add(expense1);
//        user1.getExpenses().add(expense2);
//        user2.getIncomes().add(income);
//
//        userService.update(user1);
//        userService.update(user2);


    }
}
