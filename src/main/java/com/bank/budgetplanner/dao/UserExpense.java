package com.bank.budgetplanner.dao;

import com.bank.budgetplanner.entity.Expense;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class UserExpense {

    @Id
    private Long id;
    private double income;
    private double totalExpense;
    private double leftOver;

    public UserExpense(Long id, double income, double totalExpense, double leftOver) {
        this.id = id;
        this.income = income;
        this.totalExpense = totalExpense;
        this.leftOver = leftOver;
    }

    public UserExpense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
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
