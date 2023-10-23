package com.tavsanci.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavsanci.springboot.cruddemo.dao.EmployeeRepository;
import com.tavsanci.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	//Constructor injection
	@Autowired
	public EmployeeServiceImpl (EmployeeRepository employeeRepository) {
		this.employeeRepository= employeeRepository;
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee theEmployee = null;
		if(result.isPresent()) {
			theEmployee=result.get();
		} else {
			throw new RuntimeException("The Employee did not found -Id: "+ theId);
		}
		return theEmployee;  
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}
	
	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	@Override
	public void deletebyId(int theId) {
		employeeRepository.deleteById(theId);
	}

}
