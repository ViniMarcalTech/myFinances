package com.myfinances.config;

import com.myfinances.model.entities.Category;
import com.myfinances.model.entities.PaymentMethod;
import com.myfinances.model.entities.Tag;
import com.myfinances.model.entities.User;
import com.myfinances.services.*;
import com.myfinances.shared.*;
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
    IncomeService incomeService;
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


        CategoryDTO cat1 = new CategoryDTO(null, "Alimentação", "Gastos com Alimentação");
        CategoryDTO cat2 = new CategoryDTO(null, "Combustivel", "Gastos com Combustiveis");
        CategoryDTO cat3 = new CategoryDTO(null, "Salario", "Dinheiro recebido");


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


        PaymentMethodDTO pay1 = new PaymentMethodDTO(null, "Cartão de Credito");
        PaymentMethodDTO pay2 = new PaymentMethodDTO(null, "Dinheiro");

        paymentMethodService.insert(pay1);
        paymentMethodService.insert(pay2);

        ModelMapper mapper = new ModelMapper();

        ExpenseDTO expense1 = new ExpenseDTO(null, mapper.map(user1,User.class), mapper.map(cat2, Category.class), mapper.map(pay1, PaymentMethod.class), 45.3, Instant.parse("2024-06-20T21:53:07Z"));
        ExpenseDTO expense2 = new ExpenseDTO(null, mapper.map(user2,User.class), mapper.map(cat1, Category.class), mapper.map(pay2, PaymentMethod.class), 70.3, Instant.parse("2024-06-20T21:53:07Z"));
        expense1.getTags().add(mapper.map(tag2, Tag.class));
        expense1.getTags().add(mapper.map(tag1, Tag.class));
        expense2.getTags().add(mapper.map(tag2, Tag.class));


        expenseService.insert(expense1);
        expenseService.insert(expense2);


        IncomeDTO incomeDTO = new IncomeDTO(null, mapper.map(user3, User.class), mapper.map(cat3, Category.class), 1500.5, Instant.parse("2024-06-20T21:53:07Z"));
        incomeService.insert(incomeDTO);


    }
}
