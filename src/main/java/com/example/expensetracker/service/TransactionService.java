package com.example.expensetracker.service;

import java.time.YearMonth;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.TransactionRepository;
@Service
public class TransactionService {
	@Autowired
	  private TransactionRepository transactionRepository;

	    public Transaction addTransaction(Transaction transaction) {
	        return transactionRepository.save(transaction);
	    }

	    public List<Transaction> getMonthlySummary(User user, int year, int month) {
	        var ym = YearMonth.of(year, month);
	        return transactionRepository.findAllByUserAndDateBetween(
	                user,
	                ym.atDay(1),
	                ym.atEndOfMonth()
	        );
	    }
	    public List<Transaction> getTransactionsByUserId(Long userId) {
	        return transactionRepository.findByUserId(userId);
	    }
	    public void saveAll(List<Transaction> transactions) {
	        transactionRepository.saveAll(transactions);
	    }
}
