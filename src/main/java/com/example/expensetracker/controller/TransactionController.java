package com.example.expensetracker.controller;

import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.User;
import com.example.expensetracker.service.TransactionService;
import com.example.expensetracker.service.UserService;
import com.example.expensetracker.util.FileUtil;

@RestController
@RequestMapping("/api/users/{userId}/transactions")
public class TransactionController {
	@Autowired
	private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @PostMapping
    public ResponseEntity<Transaction> addTransaction(
            @PathVariable Long userId,
            @RequestBody Transaction transaction) {
        User user = userService.getUser(userId).orElseThrow();
        transaction.setUser(user);
        return ResponseEntity.ok(transactionService.addTransaction(transaction));
    }
    @GetMapping("/summary")
    public ResponseEntity<List<Transaction>> getMonthlySummary(
            @PathVariable Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        User user = userService.getUser(userId).orElseThrow();
        return ResponseEntity.ok(transactionService.getMonthlySummary(user, year, month));
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {
        try {
            User user = userService.getUser(userId).orElseThrow();
            List<Transaction> transactions = FileUtil.parseCsv(file, user);
            transactionService.saveAll(transactions);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

}
