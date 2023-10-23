package com.tavsanci.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
	}
}
