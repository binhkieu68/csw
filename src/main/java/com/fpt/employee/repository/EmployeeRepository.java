package com.fpt.employee.repository;

import com.fpt.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access layer. Spring Data JPA auto-generates the SQL
 * (SELECT, INSERT, UPDATE, DELETE) needed to talk to the database.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
