package com.example.expensetracker.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.User;

public class FileUtil {
	 public static List<Transaction> parseCsv(MultipartFile file, User user) throws Exception {
	        List<Transaction> transactions = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	            reader.readLine(); // Skip header
	            String line;

	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                Transaction t = new Transaction();
	                t.setType(parts[0]);
	                t.setDate(LocalDate.parse(parts[1]));
	                t.setCategory(parts[2]);
	                t.setAmount(Double.parseDouble(parts[3]));
	                t.setDescription(parts[4]);
	                t.setUser(user);
	                transactions.add(t);
	            }
	        }

	        return transactions;
	    }
	 
}
