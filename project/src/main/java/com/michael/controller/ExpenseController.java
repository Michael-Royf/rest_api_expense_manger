package com.michael.controller;

import com.michael.entity.Expense;
import com.michael.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
        return expenseService.updateExpenseDetails(id, expense);
    }


    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable pageable) {
        return expenseService.getAllExpenses(pageable).toList();
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/expenses")
    private String deleteExpenseById(@RequestParam("id") Long id) {
        expenseService.deleteExpenseById(id);
        return "Expense with id " + id + " is deleted";
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpenseByCategory(@RequestParam String category, Pageable page) {
        return expenseService.readByCategory(category, page);
    }

    @GetMapping("/expenses/name")
    public List<Expense> getExpenseByName(@RequestParam String keyword, Pageable page) {
        return expenseService.readByName(keyword, page);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getExpensesByDate(@RequestParam(required = false) Date startDate,
                                            @RequestParam(required = false) Date endDate,
                                            Pageable page) {
        return expenseService.readByDate(startDate, endDate, page);
    }

}
