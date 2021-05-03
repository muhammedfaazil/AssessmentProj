package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.entity.Employee;
import com.sample.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/getEmployeeList", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody List<Employee> getAllEmployee() {
		System.out.println("Employee List API invoked!!");
		List<Employee> response = employeeService.getAllEmployee();
		return response;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody String createEmployee(@RequestBody final Employee reqData) {
		System.out.println("Save employee API invoked!!");
		Employee savedEmployee = employeeService.createEmployee(reqData);
		return savedEmployee.getId().toString();
	}

	@RequestMapping(value = "/deleteById/{Id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public @ResponseBody String deleteEmployee(@PathVariable("Id") Long Id) {
		System.out.println("Delete employee API invoked!!");
		String response = null;
		try {
			employeeService.deleteEmployee(Id);
			response = "deleting data!!";
		} catch (Exception e) {
			response = e.getMessage();
		}

		return response;
	}

}
