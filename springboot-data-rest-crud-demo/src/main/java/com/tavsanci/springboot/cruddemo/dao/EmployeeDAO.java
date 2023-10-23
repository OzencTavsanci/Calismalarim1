package com.tavsanci.springboot.cruddemo.dao;

import java.util.List;

import com.tavsanci.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(int theId);
	
	public void saveOrUpdateEmployee(Employee theEmployee);
	
	public void deleteEmployeebyId(int theId);

	
}
