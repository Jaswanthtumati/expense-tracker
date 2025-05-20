package com.example.expensetracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByUserId(Long userId);
	List<Transaction> findAllByUserAndDateBetween(User user, LocalDate start, LocalDate end);

}
