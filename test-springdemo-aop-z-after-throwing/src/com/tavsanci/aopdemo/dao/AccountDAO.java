package com.tavsanci.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;

import com.tavsanci.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	
	private String serviceCode;
	
	public List<Account> findAccounts(Boolean tripWire){
		List<Account> myAccounts = new ArrayList<Account>();
		if(tripWire){ // if(tripWire == true) İkisi aynı
			throw new RuntimeException("No soup for you!!!");
		}
		//create some new accounts
		Account temp1 = new Account("Gelincik","silver");
		Account temp2 = new Account("Papatya","Platinum");
		Account temp3 = new Account("Begonvil","Gold");
		myAccounts.add(temp3);
		myAccounts.add(temp2);
		myAccounts.add(temp1);
		return myAccounts;
	}
	public void addAccount(Account theAccount, boolean isTrue) {
		System.out.println("\n"+getClass()+" added an accountDAO to Database");
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
