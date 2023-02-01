package com.bank.budgetplanner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "monthly_income")
    private double monthlyIncome;

    public Income() {
    }

    public Income(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
