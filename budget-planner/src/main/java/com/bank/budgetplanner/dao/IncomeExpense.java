package com.bank.budgetplanner.dao;

import com.bank.budgetplanner.entity.Expense;
import com.bank.budgetplanner.entity.Income;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Optional;

@Data
public class IncomeExpense {

    private Long id;
    private Income income;
    private Expense expense;
    private double totalExpense;
    private double leftOver;

    public IncomeExpense() {
    }

    public IncomeExpense(Optional<Income> income, Expense expense, double totalExpenses, double leftOver) {
        this.id = income.get().getId();
        this.income = income.get();
        this.expense = expense;
        this.totalExpense = totalExpenses;
        this.leftOver = leftOver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getLeftOver() {
        return leftOver;
    }

    public void setLeftOver(double leftOver) {
        this.leftOver = leftOver;
    }
}
