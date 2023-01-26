package com.bank.budgetplanner.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "income")
    private double income;

    @Column(name = "total_expense")
    private double totalExpense;

    @Column(name = "left_over")
    private double leftOver;

    public User() {

    }

    public User(double income) {
        this.income = income;
        this.totalExpense = 0;
        this.leftOver = income;
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
