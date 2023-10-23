package com.tavsanci.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.tavsanci.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	
	private String serviceCode;
	
	public void addAccount(Account theAccount, boolean isTrue) {
		System.out.println(getClass()+" added an accountDAO to Database");
	}
	
	public boolean doWork() {
		System.out.println("doing some works");
		return false;
		}

	public String getName() {
		System.out.println("inside getName");
		return name;
	}

	public void setName(String name) {
		System.out.println("inside setName");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println("inside getServiceCode");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println("inside setServiceCode");
		this.serviceCode = serviceCode;
	}
	
	}
