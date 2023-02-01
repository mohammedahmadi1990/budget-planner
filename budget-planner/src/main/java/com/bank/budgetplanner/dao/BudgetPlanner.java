package com.bank.budgetplanner.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

//@Entity
@Data
public class BudgetPlanner {

    private double income;
    private double housing;
    private double householdBills;
    private double leisure;
    private double transportation;
    private double holidays;
    private double clothes;
    private double savings;

    public BudgetPlanner() {
    }

    public BudgetPlanner(double income, double housing, double householdBills,
                         double leisure, double transportation, double holidays,
                         double clothes, double savings) {
        this.income = income;
        this.housing = housing;
        this.householdBills = householdBills;
        this.leisure = leisure;
        this.transportation = transportation;
        this.holidays = holidays;
        this.clothes = clothes;
        this.savings = savings;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
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

}
