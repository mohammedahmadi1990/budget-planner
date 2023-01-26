package com.bank.budgetplanner.controller;

import com.bank.budgetplanner.entity.Expense;
import com.bank.budgetplanner.entity.User;
import com.bank.budgetplanner.exception.ResourceNotFoundException;
import com.bank.budgetplanner.repository.ExpenseRepository;
import com.bank.budgetplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/{userId}/expenses")
    public ResponseEntity<List<Expense>> getAllExpensesByUserId(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found a user with id = " + userId);
        }
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getExpensesByUserId(@PathVariable(value = "id") Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found an expense with id = " + id));
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/expenses")
    public ResponseEntity<Expense> createExpense(@PathVariable(value = "userId") Long userId,
                                              @RequestBody Expense expenseRequest) {
        Expense expense = userRepository.findById(userId).map(user -> {
            expenseRequest.setUser(user);
            return expenseRepository.save(expenseRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found a user with id = " + userId));

        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable("id") long id, @RequestBody Expense expenseRequest) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("expense ID " + id + "not found"));

        expense.setAmount(expenseRequest.getAmount());
        expense.setName(expenseRequest.getName());

        return new ResponseEntity<>(expenseRepository.save(expense), HttpStatus.OK);
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<HttpStatus> deleteExpense(@PathVariable("id") long id) {
        expenseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{userId}/expenses")
    public ResponseEntity<List<Expense>> deleteAllExpensesOfUser(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found a user with id = " + userId);
        }
        expenseRepository.deleteByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
