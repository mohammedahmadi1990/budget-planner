package com.bank.budgetplanner.dao;

import com.bank.budgetplanner.entity.Expense;
import jakarta.persistence.Column;

import java.util.List;

public class UserExpense {

    private Long id;
    private double income;
    private double totalExpense;
    private double leftOver;
    private List<Expense> expenses;

    public UserExpense(Long id, double income, double totalExpense, double leftOver, List<Expense> expenses) {
        this.id = id;
        this.income = income;
        this.totalExpense = totalExpense;
        this.leftOver = leftOver;
        this.expenses = expenses;
    }
}
