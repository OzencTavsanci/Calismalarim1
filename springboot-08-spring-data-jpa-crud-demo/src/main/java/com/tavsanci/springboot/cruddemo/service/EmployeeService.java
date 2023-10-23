package com.tavsanci.springboot.cruddemo.service;

import java.util.List;

import com.tavsanci.springboot.cruddemo.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deletebyId(int theId);

	void updateEmployee(Employee employee);

}
