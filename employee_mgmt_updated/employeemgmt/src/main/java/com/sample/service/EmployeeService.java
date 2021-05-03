package com.sample.service;

import java.util.List;

import com.sample.entity.Employee;

public interface EmployeeService {

	public Employee createEmployee(Employee employee);

	public List<Employee> getAllEmployee();

	public void deleteEmployee(Long id) throws Exception;

}
