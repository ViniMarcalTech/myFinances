package com.myfinances.myfinances.config;

import com.myfinances.myfinances.entities.*;
import com.myfinances.myfinances.repositories.*;
import com.myfinances.myfinances.services.CategoryService;
import com.myfinances.myfinances.services.TagService;
import com.myfinances.myfinances.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    TagService tagRepository;
    @Autowired
    PaymentMethodRepository paymentMethodRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryService categoryRepository;
    @Autowired
    UserService userService;


    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(null,"Marcos Vinicius","vinicius@gmail.com","9 9 9394 9299", "SenhaAbsurd4");
        User user2 = new User(null,"Maria Chica","maria@gmail.com","9 9 9299 9949", "Chaves123");
        User user3 = new User(null,"Maria Chica","maria@gmail.com","9 9 9299 9949", "Chaves123");

        userService.insert(user1);
        userService.insert(user2);
        userService.insert(user3);


        Category cat1 = new Category(null,"Alimentação", "Gastos com Alimentação");
        Category cat2 = new Category(null,"Combustivel", "Gastos com Combustiveis");
        Category cat3 = new Category(null,"Salario", "Dinheiro recebido");


        Tag tag1 = new Tag(null,"Restaurante da Tia");
        Tag tag2 = new Tag(null,"Gasolina");
        Tag tag3 = new Tag(null,"Restaurante do cleber");
        Tag tag4 = new Tag(null,"Não usei essa tag");

        PaymentMethod pay1 = new PaymentMethod(null,"Cartão de Credito");
        PaymentMethod pay2 = new PaymentMethod(null,"Dinheiro");

        Expense expense1 = new Expense(null,user1,cat1,pay1,45.3,Instant.parse("2024-06-20T21:53:07Z"));
        Expense expense2 = new Expense(null,user1,cat2,pay2,70.3,Instant.parse("2024-06-20T21:53:07Z"));
        expense1.getTags().add(tag1);
        expense2.getTags().add(tag2);
        expense1.getTags().add(tag3);
        Income income = new Income(null,user2,cat3,1500.5,Instant.parse("2024-06-20T21:53:07Z"));



        categoryRepository.insertAll(Arrays.asList(cat1,cat2,cat3));
        paymentMethodRepository.saveAll(Arrays.asList(pay1,pay2));
        tagRepository.insertAll(Arrays.asList(tag1,tag2,tag3,tag4));
        expenseRepository.saveAll(Arrays.asList(expense1,expense2));
        incomeRepository.save(income);

        user1.getExpenses().add(expense1);
        user1.getExpenses().add(expense2);
        user2.getIncomes().add(income);

        userService.update(user1);
        userService.update(user2);







    }
}
