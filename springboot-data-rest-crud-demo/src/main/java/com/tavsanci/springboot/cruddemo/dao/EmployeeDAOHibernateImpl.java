package com.tavsanci.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tavsanci.springboot.cruddemo.entity.Employee;


@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//Define field of Entity manager
	private EntityManager entityManager;
	
	//Set up Constructor Injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager= theEntityManager;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		//Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Create query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		//execute the query and get result list
		List<Employee> listEmployees = theQuery.getResultList();
		return listEmployees;
	}

	@Override
	public Employee getEmployeeById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Employee.class, theId);
	}

	@Override
	public void saveOrUpdateEmployee(Employee theEmployee) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theEmployee); // If id key=0 it will be saved, else it'll updated
	}

	@Override
	public void deleteEmployeebyId(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employee_Id");
		theQuery.setParameter("employee_Id", theId);
		theQuery.executeUpdate();
	}

}
