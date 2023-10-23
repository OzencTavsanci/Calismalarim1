package com.tavsanci.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tavsanci.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
