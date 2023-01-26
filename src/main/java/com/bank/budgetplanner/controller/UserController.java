package com.bank.budgetplanner.controller;

import com.bank.budgetplanner.dao.UserExpense;
import com.bank.budgetplanner.entity.Expense;
import com.bank.budgetplanner.entity.User;
import com.bank.budgetplanner.exception.ResourceNotFoundException;
import com.bank.budgetplanner.repository.ExpenseRepository;
import com.bank.budgetplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User theUser = userRepository.save(new User(user.getIncome()));
        return new ResponseEntity<>(theUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User theUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found a user with id = " + id));

        theUser.setIncome(user.getIncome());
        return new ResponseEntity<>(userRepository.save(theUser), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/summary/{userId}")
    public ResponseEntity<UserExpense> getSummary(@PathVariable("userId") long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("Not found a user with id = " + userId));

        List<Expense> expenses = expenseRepository.findByUserId(userId);

        double totalExpense = 0;
        for (Expense theExpense: expenses
        ) {
            totalExpense += theExpense.getAmount();
        }

        UserExpense userExpenses = new UserExpense(user.getId(),user.getIncome(),totalExpense,user.getIncome()-totalExpense);
        return new ResponseEntity<>(userExpenses, HttpStatus.OK);
    }

}
