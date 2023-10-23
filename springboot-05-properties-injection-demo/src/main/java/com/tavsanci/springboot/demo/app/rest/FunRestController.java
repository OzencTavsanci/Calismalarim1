package com.tavsanci.springboot.demo.app.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
	
	@Value("${coach.name}")
	private String coachName;
	
	@Value("team.name")
	private String teamName;
	
	@GetMapping("/teaminfo")
	public String getTeamInfo() {
		return "Team name: "+ teamName + "Coach name:"+coachName;
	}
	@GetMapping("/")
	public String sayHello() {
		return "Merhaba Dunya!! Server Time is :" + LocalDateTime.now();
	}
	
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "run 5k today!!";
	}
}
