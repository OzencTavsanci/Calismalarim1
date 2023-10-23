package com.tavsanci.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tavsanci.aopdemo.dao.AccountDAO;

public class AfterThrowingMainDemoApp {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO theAccountDAO= context.getBean("accountDAO", AccountDAO.class);
		
		
		List<Account> theAccounts = null;

		try { 
			Boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(false);
	
		} catch (Exception exc) {
			System.out.println("\n\nMain Program........ caught execption: "+ exc);
		}

		//display the accounts
		System.out.println("\n\nMain Program: AfterThrowingMainDemoApp");
		System.out.println("------");

		System.out.println(theAccounts);
		System.out.println("\n");
		context.close();
	}

}
