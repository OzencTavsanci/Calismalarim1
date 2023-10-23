package com.tavsanci.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tavsanci.aopdemo.dao.AccountDAO;

public class AfterReturingMainDemo {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO theAccountDAO= context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = theAccountDAO.findAccounts();
		
		//display the accounts
		System.out.println("\n\nMain Program: AfterReturningDemo");
		System.out.println("------");
		
		System.out.println(theAccounts);
		System.out.println("\n");
		context.close();
	}

}
