package com.sample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
