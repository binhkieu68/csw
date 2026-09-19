package com.fpt.employee.service;

import com.fpt.employee.model.Employee;
import com.fpt.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic layer exposing the three web service operations
 * required by the assignment:
 *   1. getEmployees()            -> retrieve all employees
 *   2. addEmployee(Employee e)   -> add a new employee record
 *   3. updateEmployee(Employee e)-> modify an existing employee record
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /** 1. getEmployees: retrieve all employees in an array/list */
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    /** 2. addEmployee: add a new employee record */
    public Employee addEmployee(Employee e) {
        // Ensure this is treated as a NEW record even if a client sent an id
        e.setId(null);
        return employeeRepository.save(e);
    }

    /** 3. updateEmployee: modify an existing employee record */
    public Employee updateEmployee(Long id, Employee e) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        existing.setName(e.getName());
        existing.setSalary(e.getSalary());
        return employeeRepository.save(existing);
    }
}
