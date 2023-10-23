package com.tavsanci.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tavsanci.springboot.cruddemo.dao.EmployeeDAO;
import com.tavsanci.springboot.cruddemo.entity.Employee;
import com.tavsanci.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	//Inject Employee dao in a dirty way
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	//expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
	
		return  employeeService.getAllEmployees();
	}
	
	@GetMapping("/employees/{employee_Id}")
	public Employee getEmployeeById(@PathVariable int employee_Id) {
		Employee employee = employeeService.getEmployeeById(employee_Id);
		if(employee == null) {
			throw new RuntimeException(" Employee id not found "+ employee_Id);
		}
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		employeeService.saveEmployee(theEmployee);
		return theEmployee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.updateEmployee(theEmployee);
		return theEmployee;
	}
	
	@DeleteMapping("/employees/{employee_Id}")
	public String deleteEmployee(@PathVariable int employee_Id) {
		Employee theEmployee = employeeService.getEmployeeById(employee_Id);
		if(theEmployee== null) {
			throw new RuntimeException(" Employee id not found"+ employee_Id);
		}
		employeeService.deleteEmployeebyId(employee_Id);

		return "Deleted Employee - "+ employee_Id;
	}

	
}
