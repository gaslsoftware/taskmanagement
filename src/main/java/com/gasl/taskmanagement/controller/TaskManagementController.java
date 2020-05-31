package com.gasl.taskmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskManagementController {
	
	
	
	@GetMapping("/tester")
	public String tester() {
		return "Hello World";
	}

}
