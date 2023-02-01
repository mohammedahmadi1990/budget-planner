package com.bank.budgetplanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "housing")
    private double housing;

    @Column(name = "household_bills")
    private double householdBills;

    @Column(name = "leisure")
    private double leisure;

    @Column(name = "transportation")
    private double transportation;

    @Column(name = "holidays")
    private double holidays;

    @Column(name = "clothes")
    private double clothes;

    @Column(name = "savings")
    private double savings;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "income_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Income income;

    public Expense(double housing, double householdBills, double leisure, double transportation, double holidays, double clothes, double savings, Income income) {
        this.housing = housing;
        this.householdBills = householdBills;
        this.leisure = leisure;
        this.transportation = transportation;
        this.holidays = holidays;
        this.clothes = clothes;
        this.savings = savings;
        this.income = income;
    }

    public Expense() {
    }

    public double getTotalExpenses(){
        double totalExpenses = 0;
        totalExpenses += getHousing();
        totalExpenses += getHouseholdBills();
        totalExpenses += getLeisure();
        totalExpenses += getTransportation();
        totalExpenses += getHolidays();
        totalExpenses += getClothes();
        totalExpenses += getSavings();

        return totalExpenses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHousing() {
        return housing;
    }

    public void setHousing(double housing) {
        this.housing = housing;
    }

    public double getHouseholdBills() {
        return householdBills;
    }

    public void setHouseholdBills(double householdBills) {
        this.householdBills = householdBills;
    }

    public double getLeisure() {
        return leisure;
    }

    public void setLeisure(double leisure) {
        this.leisure = leisure;
    }

    public double getTransportation() {
        return transportation;
    }

    public void setTransportation(double transportation) {
        this.transportation = transportation;
    }

    public double getHolidays() {
        return holidays;
    }

    public void setHolidays(double holidays) {
        this.holidays = holidays;
    }

    public double getClothes() {
        return clothes;
    }

    public void setClothes(double clothes) {
        this.clothes = clothes;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }
}
