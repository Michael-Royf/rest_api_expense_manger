package com.michael.service;

import com.michael.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;


public interface ExpenseService {

    Expense saveExpenseDetails(Expense expense);

    Page<Expense> getAllExpenses(Pageable pageable);

    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense updateExpenseDetails(Long id, Expense expense);

    List<Expense> readByCategory(String category, Pageable page);

    List<Expense> readByName(String keyword, Pageable page);

    List<Expense> readByDate(Date startDate, Date endDate, Pageable page);
}
