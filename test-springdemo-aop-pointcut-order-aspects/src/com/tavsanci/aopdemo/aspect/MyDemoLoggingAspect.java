package com.tavsanci.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect { 
	
	//@Before("execution(public void addAccount())")
	//@Before("execution(public void add*())")
	@Before("com.tavsanci.aopdemo.aspect.AopPointcutExpressions.forDaoPackageNoGetterNoSetter()") 
	public void beforeAddAccountAdvice() {
		System.out.println("\n===>>>> Executing @Before advice on beforeAddAccountAdvice()");
	}

}
