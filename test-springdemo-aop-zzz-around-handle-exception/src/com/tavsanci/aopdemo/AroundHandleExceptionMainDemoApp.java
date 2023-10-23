package com.tavsanci.aopdemo;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.tavsanci.aopdemo.dao.AccountDAO;
import com.tavsanci.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionMainDemoApp {

	private static Logger myLogger = Logger.getLogger(AroundHandleExceptionMainDemoApp.class.getName()); // Best practise class'ın ismini vermek
	
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//Get the bean from Spring Container
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		myLogger.info("Calling getFortune method");
		boolean tripWire = true;
		String data = theFortuneService.getFortune(tripWire);
		
		myLogger.info("My Fortune is: "+data);
		myLogger.info("Finished");

		context.close();
	}

}
