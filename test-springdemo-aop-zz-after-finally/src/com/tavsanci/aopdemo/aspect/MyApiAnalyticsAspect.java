package com.tavsanci.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect 
@Component
@Order(2)
public class MyApiAnalyticsAspect {

	@Before("com.tavsanci.aopdemo.aspect.AopPointcutExpressions.forDaoPackageNoGetterNoSetter()")
	public void performApiAnylatics() {
		System.out.println("\n<<<<=== Performing Api analytics");
	}
}
