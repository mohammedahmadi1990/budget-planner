package com.bank.budgetplanner.repository;

import com.bank.budgetplanner.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense findByIncomeId(Long incomeId);
}
