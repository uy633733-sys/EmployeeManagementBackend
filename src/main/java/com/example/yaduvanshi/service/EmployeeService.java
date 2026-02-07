package com.example.yaduvanshi.service;

import com.example.yaduvanshi.model.Employee;
import com.example.yaduvanshi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Employee update(Long id, Employee updatedEmployee) {

        Employee existing = getById(id);

        if (!existing.getEmail().equals(updatedEmployee.getEmail())
                && repository.existsByEmail(updatedEmployee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        existing.setFirstName(updatedEmployee.getFirstName());
        existing.setLastName(updatedEmployee.getLastName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setPhone(updatedEmployee.getPhone());
        existing.setSalary(updatedEmployee.getSalary());
        existing.setDesignation(updatedEmployee.getDesignation());

        return repository.save(existing);
    }


    public List<Employee> search(String keyword) {
        return repository.findByFirstNameContainingIgnoreCase(keyword);
    }
}
