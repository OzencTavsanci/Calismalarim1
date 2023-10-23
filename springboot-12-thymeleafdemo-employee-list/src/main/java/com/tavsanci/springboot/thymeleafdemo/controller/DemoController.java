package com.tavsanci.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tavsanci.springboot.thymeleafdemo.entity.Employee;

@Controller
@RequestMapping("/employees")
public class DemoController {

	@GetMapping("/list")
	public String findAll(Model theModel) {
		Employee theEmployee1 = new Employee("Ozenc", "Tavsanci", "ozenctavsanci@hotmail.com");
		Employee theEmployee2 = new Employee("Merve", "Merve", "mervemerve@hotmail.com");
		Employee theEmployee3 = new Employee("Asli", "Asli", "asliasli@hotmail.com");
		
		List<Employee> employeesList = new ArrayList<>();
		employeesList.add(theEmployee1);
		employeesList.add(theEmployee2);
		employeesList.add(theEmployee3);
		
		theModel.addAttribute("employees", employeesList);
		
		return "list-employees";
	}
}
