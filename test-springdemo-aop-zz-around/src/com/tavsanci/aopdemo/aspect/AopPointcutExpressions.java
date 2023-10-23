package com.tavsanci.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect	
@Component 
public class AopPointcutExpressions {

	// this is where  we all add all of our related advices for logging
	
	//let's start with an @Before advice
	
	@Pointcut("execution(* com.tavsanci.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {
	}
	
	@Pointcut("execution(* com.tavsanci.aopdemo.dao.*.get*(..))")
	public void getter() {
	}
	
	@Pointcut("execution(* com.tavsanci.aopdemo.dao.*.set*(..))")
	public void setter() {
	}
	
	// create pointcut: include getters and setters
	@Pointcut("forDaoPackage() && !( getter()|| setter())")
	public void forDaoPackageNoGetterNoSetter() {
	}
}
