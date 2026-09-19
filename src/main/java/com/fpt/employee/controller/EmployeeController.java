package com.fpt.employee.controller;

import com.fpt.employee.model.Employee;
import com.fpt.employee.service.EmployeeNotFoundException;
import com.fpt.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Web service endpoints (REST/JSON) for employee management.
 *
 * GET    /api/employees        -> getEmployees()
 * POST   /api/employees        -> addEmployee(Employee e)
 * PUT    /api/employees/{id}   -> updateEmployee(Employee e)
 */
@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*") // allow the test web app (index.html) to call this from the browser
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. getEmployees
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    // 2. addEmployee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee e) {
        Employee saved = employeeService.addEmployee(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 3. updateEmployee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                     @Valid @RequestBody Employee e) {
        Employee updated = employeeService.updateEmployee(id, e);
        return ResponseEntity.ok(updated);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EmployeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
