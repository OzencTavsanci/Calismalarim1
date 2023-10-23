package com.tavsanci.aopdemo.aspect;

import java.security.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.tavsanci.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint ) throws Throwable  {
		//print out method we are advising on
		myLogger.info("\n>>>> Executing @Around advice on method: "+theProceedingJoinPoint.getSignature().toShortString());
		//get begin timestamp
		long begin = System.currentTimeMillis();
		//now, execute the method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			//log the exception
			myLogger.warning(e.getMessage());
//			// give user a custom message
//			result = "Major accident! But no worries"+" Your private aop helicopter is on the way!";
			throw e;
		} 
		//get end timestamp
		long end = System.currentTimeMillis();
		//compute duration and display it
		long duration = end-begin;
		myLogger.info("\n>>>> Duration: "+ duration/1000.0+" seconds");
		return result;
	}
	
	//Add a new advice gor @AfterFinally on the findAccounts() method
	
	@After("execution(* com.tavsanci.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		String methodName = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n>>>>> Executing @After (Finally) on method"+methodName);
	}
	
	// Add a new advice for @AfterThrowing on the findAccounts() method
	@AfterThrowing(
				pointcut=" execution(* com.tavsanci.aopdemo.dao.AccountDAO.findAccounts(..))",
				throwing = "theThrowable")
	public void afterThrowingFindAccountsAdvice ( JoinPoint theJoinPoint, Throwable theThrowable ) {
		
		//Print out which method we are advising on...
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n>>>> Executing @AfterThrowing on method: "+ method);
		
		//log the exception
		myLogger.info("\n>>>> The Exception is: "+theThrowable);
		
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
		myLogger.info("\n>>>> Executing @AfterReturning on method Short Name: "+ method);
		myLogger.info("\n>>>> Executing @AfterReturning on method Long Name: "+ method1);
		
		//print out the results of the method call
		myLogger.info("\n>>>> The Result is:  "+ result);
		//let's post-process the dat.. let's modify it
		
		// conver The account name to uppercase
		convertAccountNamesToUpperCase(result);
		myLogger.info("\n>>>> The Result is:  "+ result);

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
		myLogger.info("\n===>>>> Executing @Before advice on beforeAddAccountAdvice()");
		
		//Display the method signature 
		MethodSignature theSignature = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("Method: "+ theSignature);
		
		// display the method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		//loop through args							
		for (Object tempArgs : args) {
			myLogger.info(tempArgs.toString());		// My logger only print Strings, so we need to use toString.	
			if (tempArgs instanceof Account) {
			//Downcast and print Account specific data 
				Account new_name = (Account) tempArgs;
				myLogger.info("Account_Name: "+new_name.getName());
				myLogger.info("Account_Level: "+new_name.getLevel());
				
			}
		}
	}

}
