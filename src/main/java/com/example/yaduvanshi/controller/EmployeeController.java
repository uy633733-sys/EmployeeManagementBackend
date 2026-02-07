package com.example.yaduvanshi.controller;

import com.example.yaduvanshi.model.Employee;
import com.example.yaduvanshi.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Long id,
            @RequestBody Employee employee) {
        return service.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<Employee> search(@RequestParam String keyword) {
        return service.search(keyword);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public String handleRuntime(RuntimeException ex) {
        return ex.getMessage();
    }

}

