package com.tavsanci.aopdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tavsanci.aopdemo.dao.AccountDAO;
import com.tavsanci.aopdemo.dao.MembershipDAO;

public class MainDemo {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO theAccountDAO= context.getBean("accountDAO", AccountDAO.class);
		
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		Account myAccount = new Account();
		
		Account myAccount2 = new Account();
		myAccount2.setName("Özenç");
		myAccount2.setLevel("Üst Düzey");
		
		theAccountDAO.addAccount( myAccount, true);
		theAccountDAO.doWork();
		
		theAccountDAO.addAccount( myAccount2,false);

		
		System.out.println("\n>>>>>>>>\n");
		
		membershipDAO.addMembershipDAO();
		membershipDAO.goSleep();
		
		System.out.println("\n>>>>>>>>\n");
		
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("gold");
		
		System.out.println("\n>>>>>>>>\n");
		
		String name = theAccountDAO.getName();
		String serviceCode = theAccountDAO.getServiceCode();
		context.close();
	}

}
