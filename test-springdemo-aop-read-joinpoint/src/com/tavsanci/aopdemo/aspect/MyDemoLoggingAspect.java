package com.tavsanci.aopdemo.aspect;

import java.util.Iterator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.tavsanci.aopdemo.Account;





@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect { 
	
	//@Before("execution(public void addAccount())")
	//@Before("execution(public void add*())")
	@Before("com.tavsanci.aopdemo.aspect.AopPointcutExpressions.forDaoPackageNoGetterNoSetter()") 
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n===>>>> Executing @Before advice on beforeAddAccountAdvice()");
		
		//Display the method signature 
		MethodSignature theSignature = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method: "+ theSignature);
		
		// display the method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		//loop through args							
		for (Object tempArgs : args) {
			System.out.println(tempArgs);			
			if (tempArgs instanceof Account) {
			//Downcast and print Account specific data 
				Account new_name = (Account) tempArgs;
				System.out.println("Account_Name: "+new_name.getName());
				System.out.println("Account_Level: "+new_name.getLevel());
				
			}
		}
	}

}
