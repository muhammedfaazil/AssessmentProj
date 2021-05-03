package com.sample.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.entity.Employee;
import com.sample.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	EmployeeService employeeService;

	@Test
	public void testGetAllEmployee() throws Exception {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(1L, "Mukesh", "Kumar", "mukesh@gmail.com", "9856374555"));
		employeeList.add(new Employee(2L, "Prasad", "Shetty", "prasd@gmail.com", "9856374555"));
		employeeList.add(new Employee(3L, "Abishek", "Raja", "abisk@gmail.com", "9856374555"));
		employeeList.add(new Employee(4L, "Akash", "Patel", "akap@gmail.com", "9856374555"));

		Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeList);

		ResultActions res = mockMvc.perform(get("/getEmployeeList")).andExpect(status().isOk());

		System.out.println("Test status - " + res.andReturn().getResponse().getStatus());

		MvcResult mvcResult = res.andReturn();
		String responseJson = mvcResult.getResponse().getContentAsString();

		String expectedJson = objectMapper.writeValueAsString(employeeList);

		assertThat(expectedJson).isEqualToIgnoringWhitespace(responseJson);
	}

	@Test
	public void testSaveEmployee() throws Exception {

		Employee newEmployee = new Employee();
		newEmployee.setFirstName("Mukesh");
		newEmployee.setLastName("Kumar");
		newEmployee.setEmailId("mukesh@gmail.com");
		newEmployee.setPhoneNo("9856374555");

		Employee savedEmployee = new Employee(1L, "Mukesh", "Kumar", "mukesh@gmail.com", "9856374555");

		Mockito.when(employeeService.createEmployee(ArgumentMatchers.any())).thenReturn(savedEmployee);

		ResultActions res = mockMvc.perform(post("/saveEmployee").contentType("application/json")
				.content(objectMapper.writeValueAsString(newEmployee))).andExpect(status().isOk());

		System.out.println("Test status - " + res.andReturn().getResponse().getStatus());

		MvcResult mvcResult = res.andReturn();

		String responseJson = mvcResult.getResponse().getContentAsString();

		assertThat("1").isEqualToIgnoringWhitespace(responseJson);

	}

	@Test
	public void testDeleteEmployee() throws Exception {

		Long deleteEmpId = 1L;

		Mockito.doNothing().when(employeeService).deleteEmployee(deleteEmpId);

		ResultActions res = mockMvc.perform(delete("/deleteById/1")).andExpect(status().isOk());

		System.out.println("Test status - " + res.andReturn().getResponse().getStatus());

		Mockito.verify(employeeService, times(1)).deleteEmployee(deleteEmpId);

	}
}
