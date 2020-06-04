package com.gasl.taskmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gasl.taskmanagement.bo.TaskManagementBo;
import com.gasl.taskmanagement.dto.Tasks;
import com.gasl.taskmanagement.vo.RequestResponse;

@RestController
public class TaskManagementController {
	
	@Autowired
	private TaskManagementBo taskManagementBO;
	
	
	
	@GetMapping("/tester")
	public String tester() {
		return "Hello World";
	}
	
	@PostMapping("/createnewtask")
	public RequestResponse createNewTask(@RequestBody Tasks task) {
		/*
		 * RequestResponse requestResponse = new RequestResponse(); Respo
		 * ResponseEntity.status(HttpStatus.OK); ResponseEntity.
		 */
		Map<String, Object> model = new HashMap<String, Object>();
		
		String taskId = taskManagementBO.createNewTask(task);
		model.put("taskId", taskId);
		RequestResponse requestResponse = new RequestResponse();
		requestResponse.setHasError(true);
		requestResponse.setMessage("success");
		requestResponse.setModel(taskId);

		return requestResponse;
	}

}
