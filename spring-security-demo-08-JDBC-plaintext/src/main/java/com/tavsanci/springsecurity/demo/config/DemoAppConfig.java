package com.tavsanci.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;
import javax.xml.bind.PropertyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.tavsanci.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	// Set up variable to hold properties
	@Autowired
	private Environment env;
	
	//Set up a logger for diagnostics
	private Logger logger= Logger.getLogger(getClass().getName());
	
	//Define a view resolver 
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	//Define a bean for our datasource
	@Bean
	public DataSource securityDataSource() throws PropertyVetoException {
	
		//define a conneciton pool
		HikariDataSource securityDataSource = new HikariDataSource();
		
		try {
			securityDataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}
		//log  connection props
		logger.info(">>>jdbc.url= "+env.getProperty("jdbc.url"));
		logger.info(">>>jdbc.user= "+env.getProperty("jdbc.user"));

		
		//set  database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUsername(env.getProperty(("jdbc.user")));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//set connection pool props
		securityDataSource.setMinimumIdle(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaximumPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMinimumIdle(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	//Helper convert class to get int 
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		int propValInt = Integer.parseInt(propVal);
		return propValInt;
	}
	
}
