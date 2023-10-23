package com.tavsanci.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.tavsanci.aopdemo.dao.AccountDAO;
import com.tavsanci.aopdemo.service.TrafficFortuneService;

public class AroundMainDemoApp {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//Get the bean from Spring Container
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		System.out.println("Calling getFortune method");
		String data = theFortuneService.getFortune();
		
		System.out.println("My Fortune is: "+data);
		System.out.println("Finished");

		context.close();
	}

}
