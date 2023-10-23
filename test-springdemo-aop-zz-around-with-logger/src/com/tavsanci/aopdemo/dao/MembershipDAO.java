package com.tavsanci.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public Boolean addMembershipDAO() {
		System.out.println(getClass()+" added an membershipDAO to Database");
		return false;
	}
	public void goSleep() {
		System.out.println("I'm going sleep");
	}
	
}
