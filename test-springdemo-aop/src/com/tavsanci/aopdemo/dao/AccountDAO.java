package com.tavsanci.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.tavsanci.aopdemo.Account;

@Component
public class AccountDAO {
	
	public void addAccount(Account theAccount, boolean isTrue) {
		System.out.println(getClass()+" added an accountDAO to Database");
	}
	
	public boolean doWork() {
		System.out.println("doing some works");
		return false;
		}
	}
