package com.tavsanci.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyDemoLoggingAspect {

	// this is where  we all add all of our related advices for logging
	
	//let's start with an @Before advice
	
	@Pointcut("execution(* com.tavsanci.aopdemo.dao.*.*(..))")
	public void pointCutForDaoPackage() {
	}
	
	
	//@Before("execution(public void addAccount())")
	//@Before("execution(public void add*())")
	@Before("pointCutForDaoPackage()") 
	public void beforeAddAccountAdvice() {
		System.out.println("===>>>> Executing @Before advice on beforeAddAccountAdvice()");
	}
	@Before("pointCutForDaoPackage()")
	public void performApiAnylatics() {
		System.out.println("<<<<=== Executing @Before advice on performApiAnylatics()");
	}
}
