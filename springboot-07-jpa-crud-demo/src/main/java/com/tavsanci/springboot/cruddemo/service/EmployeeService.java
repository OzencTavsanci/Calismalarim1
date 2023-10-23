package com.tavsanci.springboot.cruddemo.service;

import java.util.List;

import com.tavsanci.springboot.cruddemo.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(int theId);
	
	public void saveEmployee(Employee theEmployee);
	
	public void deleteEmployeebyId(int theId);

	void updateEmployee(Employee employee);

}
