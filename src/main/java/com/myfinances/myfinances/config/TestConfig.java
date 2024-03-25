package com.myfinances.myfinances.config;

import com.myfinances.myfinances.model.entities.*;
import com.myfinances.myfinances.services.*;
import com.myfinances.myfinances.shared.TagDTO;
import com.myfinances.myfinances.shared.UserDTO;
import org.modelmapper.ModelMapper;
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
    TagService tagService;
    @Autowired
    PaymentMethodService paymentMethodService;
    @Autowired
    IncomeService incomeRepository;
    @Autowired
    ExpenseService expenseService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;


    @Override
    public void run(String... args) throws Exception {

        UserDTO user1 = new UserDTO(null, "Marcos Vinicius", "vinicius@gmail.com", "9 9 9394 9299", "SenhaAbsurd4");
        UserDTO user2 = new UserDTO(null, "Maria Chica", "maria@gmail.com", "9 9 9299 9949", "Chaves123");
        UserDTO user3 = new UserDTO(null, "Maria Chica", "maria@gmail.com", "9 9 9299 9949", "Chaves123");

        userService.insert(user1);
        userService.insert(user2);
        userService.insert(user3);


        Category cat1 = new Category(null, "Alimentação", "Gastos com Alimentação");
        Category cat2 = new Category(null, "Combustivel", "Gastos com Combustiveis");
        Category cat3 = new Category(null, "Salario", "Dinheiro recebido");


        categoryService.insert(cat1);
        categoryService.insert(cat2);
        categoryService.insert(cat3);


        TagDTO tag1 = new TagDTO(null, "Restaurante da Tia");
        TagDTO tag2 = new TagDTO(null, "Gasolina");
        TagDTO tag3 = new TagDTO(null, "Restaurante do cleber");
        TagDTO tag4 = new TagDTO(null, "Não usei essa tag");

        tagService.insert(tag1);
        tagService.insert(tag2);
        tagService.insert(tag3);
        tagService.insert(tag4);


        PaymentMethod pay1 = new PaymentMethod(null, "Cartão de Credito");
        PaymentMethod pay2 = new PaymentMethod(null, "Dinheiro");

        paymentMethodService.insert(pay1);
        paymentMethodService.insert(pay2);

//
//        Expense expense1 = new Expense(null, new ModelMapper().map(user1,User.class), cat2, pay1, 45.3, Instant.parse("2024-06-20T21:53:07Z"));
//        Expense expense2 = new Expense(null, new ModelMapper().map(user2,User.class), cat1, pay2, 70.3, Instant.parse("2024-06-20T21:53:07Z"));
//        expense1.getTags().addAll(Arrays.asList(tag1, tag2));
//        expense2.getTags().add(tag2);
//
//        expenseService.insert(expense1);
//        expenseService.insert(expense2);


        Income income = new Income(null, new ModelMapper().map(user3,User.class), cat3, 1500.5, Instant.parse("2024-06-20T21:53:07Z"));
        incomeRepository.insert(income);


    }
}
