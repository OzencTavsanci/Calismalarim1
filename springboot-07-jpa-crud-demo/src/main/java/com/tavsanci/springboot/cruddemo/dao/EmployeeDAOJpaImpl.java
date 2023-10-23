package com.tavsanci.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tavsanci.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager; 
	
	@Autowired
	public EmployeeDAOJpaImpl (EntityManager theEntityManager) {
		this.entityManager=theEntityManager;
	}
	@Override
	public List<Employee> findEmployees() {
		
		//Create a Query
		Query theQuery = entityManager.createQuery("from Employee");
		
		//execute query and get resultlist
		List<Employee> employees= theQuery.getResultList();
		
		//return the result
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		Employee employee = entityManager.find(Employee.class, theId);
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		Employee dbEmployee = entityManager.merge(theEmployee);
		theEmployee.setId(dbEmployee.getId());
		
	}

	@Override
	public void delete(int theId) {
		Query theQuery = entityManager.createQuery(" delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
	}

}
