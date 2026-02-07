package com.example.yaduvanshi.repository;

import com.example.yaduvanshi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstNameContainingIgnoreCase(String keyword);

    boolean existsByEmail(String email);
}
