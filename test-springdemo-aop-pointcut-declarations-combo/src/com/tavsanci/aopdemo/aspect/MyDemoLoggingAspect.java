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
	private void pointCutForDaoPackage() {
	}
	
	@Pointcut("execution(* com.tavsanci.aopdemo.dao.*.get*(..))")
	private void getter() {
	}
	
	@Pointcut("execution(* com.tavsanci.aopdemo.dao.*.set*(..))")
	private void setter() {
	}
	
	// create pointcut: include getters and setters
	@Pointcut("pointCutForDaoPackage() && !( getter()|| setter())")
	private void forDaoPackageNoGetterNoSetter() {
	}
	
	//@Before("execution(public void addAccount())")
	//@Before("execution(public void add*())")
//	@Before("pointCutForDaoPackage()") 
//	public void beforeAddAccountAdvice() {
//		System.out.println("===>>>> Executing @Before advice on beforeAddAccountAdvice()");
//	}

	
	@Before("forDaoPackageNoGetterNoSetter()")
	public void performApiAnylatics() {
		System.out.println("<<<<=== Executing @Before advice on performApiAnylatics()");
	}
}
