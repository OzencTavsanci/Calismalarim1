package com.tavsanci.springboot.cruddemo.dao;

import java.util.List;

import com.tavsanci.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findEmployees();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void delete(int theId);

	
}
