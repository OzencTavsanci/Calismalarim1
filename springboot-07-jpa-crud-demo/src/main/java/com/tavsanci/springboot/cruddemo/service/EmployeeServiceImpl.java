package com.tavsanci.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavsanci.springboot.cruddemo.dao.EmployeeDAO;
import com.tavsanci.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeDAO employeeDAO;
	
	//Constructor injection
	@Autowired
	public EmployeeServiceImpl (@Qualifier("employeeDAOJpaImpl") EmployeeDAO employeeDAO) {
		this.employeeDAO= employeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		return employeeDAO.findEmployees();
	}

	@Override
	@Transactional
	public Employee getEmployeeById(int theId) {
		return  employeeDAO.findById(theId);
	}

	@Override
	@Transactional
	public void saveEmployee(Employee theEmployee) {
		employeeDAO.save(theEmployee);
	}
	
	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		employeeDAO.save(employee);
	}
	
	@Override
	@Transactional
	public void deleteEmployeebyId(int theId) {
		employeeDAO.delete(theId);
	}

}
