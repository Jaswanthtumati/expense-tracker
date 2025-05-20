package com.example.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensetracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
