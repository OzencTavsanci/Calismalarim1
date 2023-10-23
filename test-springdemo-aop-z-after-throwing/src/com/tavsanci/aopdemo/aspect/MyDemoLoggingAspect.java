package com.tavsanci.aopdemo.aspect;

import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	
	// Add a new advice for @AfterThrowing on the findAccounts() method
	@AfterThrowing(
				pointcut=" execution(* com.tavsanci.aopdemo.dao.AccountDAO.findAccounts(..))",
				throwing = "theThrowable")
	public void afterThrowingFindAccountsAdvice ( JoinPoint theJoinPoint, Throwable theThrowable ) {
		
		//Print out which method we are advising on...
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n>>>> Executing @AfterThrowing on method: "+ method);
		
		//log the exception
		System.out.println("\n>>>> The Exception is: "+theThrowable);
		
	}
	
	//Add a new advice for @AfterReturning on the findAccounts() method
	@AfterReturning(
			pointcut="execution(* com.tavsanci.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result"
					)
	public void afterReturningFindAccountsAdvice ( JoinPoint theJoinPoint, List<Account> result ) {
		
		//print out which method we are advising on 
		String method = theJoinPoint.getSignature().toShortString();
		String method1 = theJoinPoint.getSignature().toLongString();
		System.out.println("\n>>>> Executing @AfterReturning on method Short Name: "+ method);
		System.out.println("\n>>>> Executing @AfterReturning on method Long Name: "+ method1);
		
		//print out the results of the method call
		System.out.println("\n>>>> The Result is:  "+ result);
		//let's post-process the dat.. let's modify it
		
		// conver The account name to uppercase
		convertAccountNamesToUpperCase(result);
		System.out.println("\n>>>> The Result is:  "+ result);

	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		for (Account tempAccount : result) {
			String tempName = tempAccount.getName().toUpperCase();
			tempAccount.setName(tempName);
		} 
	}

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
