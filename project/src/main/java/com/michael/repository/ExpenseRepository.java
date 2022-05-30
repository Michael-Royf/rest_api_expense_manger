package com.michael.repository;

import com.michael.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable page);

    Page<Expense> findByUserIdAndNameContaining(Long userId, String keyword, Pageable page);

    @Query(
            value = "SELECT * FROM tbl_expenses WHERE created_at BETWEEN 'startDate' AND 'endDate'",
            nativeQuery = true)
    Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page);

    Page<Expense> findByUserId(long userId, Pageable page);

    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);




}
