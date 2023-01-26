package com.bank.budgetplanner.controller;

import com.bank.budgetplanner.entity.Expense;
import com.bank.budgetplanner.entity.User;
import com.bank.budgetplanner.repository.ExpenseRepository;
import com.bank.budgetplanner.repository.UserRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.io.IOException;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;


@WebMvcTest(UserController.class)
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ExpenseRepository expenseRepository;

    @Test
    void createUser() throws Exception {
        User user = new User(1000.0);
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(user)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getSummaryTest1() throws Exception {
        User newUser = new User(1000.0);
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.postForObject("http://localhost:8080/api/users", newUser, User.class);

        assertEquals(1000, user.getIncome());
    }

    @Test
    void getSummaryTest2() throws Exception {
        User newUser = new User(5000);
        newUser.setId(1L);
        RestTemplate restTemplate = new RestTemplate();

        Expense newExpense = new Expense();
        newExpense.setUser(newUser);
        newExpense.setName("Housing");
        newExpense.setAmount(700);
        restTemplate.postForObject("http://localhost:8080/api/users/1/expenses", newExpense, Expense.class);

        assertEquals(4300, userRepository.findById(1L).get().getIncome()-700);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected String toJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T fromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}