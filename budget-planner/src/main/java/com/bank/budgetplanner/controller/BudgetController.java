package com.bank.budgetplanner.controller;

import com.bank.budgetplanner.dao.BudgetPlanner;
import com.bank.budgetplanner.dao.IncomeExpense;
import com.bank.budgetplanner.entity.Expense;
import com.bank.budgetplanner.entity.Income;
import com.bank.budgetplanner.repository.ExpenseRepository;
import com.bank.budgetplanner.repository.IncomeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500") // frontend server
@RestController
@RequestMapping("/api/budget/")
public class BudgetController {
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    ExpenseRepository expenseRepository;


    @GetMapping("/planner")
    public ResponseEntity<List<IncomeExpense>> getAllBudgetPlanned() {
        double totalExpenses = 0;
        double leftOver = 0;
        List<Income> incomes = new ArrayList<>();
        List<IncomeExpense> incomeExpenseList = new ArrayList<>();
        incomeRepository.findAll().forEach(incomes::add);

        for (Income income : incomes) {
            Expense expense = expenseRepository.findByIncomeId(income.getId());
            totalExpenses = expense.getTotalExpenses();
            leftOver = income.getMonthlyIncome() - totalExpenses;
            incomeExpenseList.add(new IncomeExpense(Optional.of(income), expense, totalExpenses, leftOver));
        }
        return new ResponseEntity<>(incomeExpenseList, HttpStatus.OK);
    }

    @GetMapping("/planner/{id}")
    public ResponseEntity<IncomeExpense> getAllBudgetPlanned(@PathVariable("id") long id) {
        double totalExpenses = 0;
        double leftOver = 0;
        Optional<Income> income = incomeRepository.findById(id);
        Expense expense = expenseRepository.findByIncomeId(id);
        totalExpenses = expense.getTotalExpenses();
        leftOver = income.get().getMonthlyIncome() - totalExpenses;

        return new ResponseEntity<>(new IncomeExpense(income, expense, totalExpenses, leftOver), HttpStatus.OK);
    }


    @PostMapping("/planner")
    public ResponseEntity<IncomeExpense> createBudgetPlanner(@RequestBody BudgetPlanner budgetPlanner) {
        Income theIncome = new Income(budgetPlanner.getIncome());
        incomeRepository.save(theIncome);
        Expense expenses = new Expense(budgetPlanner.getHousing(),
                budgetPlanner.getHouseholdBills(),
                budgetPlanner.getLeisure(),
                budgetPlanner.getTransportation(),
                budgetPlanner.getHolidays(),
                budgetPlanner.getClothes(),
                budgetPlanner.getSavings(),
                theIncome);
        expenseRepository.save(expenses);
        double totalExpenses = expenses.getTotalExpenses();
        double leftOver = budgetPlanner.getIncome() - totalExpenses;
        IncomeExpense incomeExpense = new IncomeExpense(Optional.of(theIncome), expenses, totalExpenses, leftOver);

        return new ResponseEntity<>(incomeExpense, HttpStatus.CREATED);
    }



}
