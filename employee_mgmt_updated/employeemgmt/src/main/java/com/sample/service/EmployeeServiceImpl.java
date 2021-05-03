package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.entity.Employee;
import com.sample.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public Employee createEmployee(Employee employee) {
		System.out.println("saving employee data!!");
		Employee savedEmployee = employeeRepo.save(employee);
		return savedEmployee;
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();
	}

	@Override
	public void deleteEmployee(Long id) throws Exception {
		try {
			employeeRepo.deleteById(id);
		} catch (Exception e) {
			throw e;
		}

	}

}
