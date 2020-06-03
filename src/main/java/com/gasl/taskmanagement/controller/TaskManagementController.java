package com.gasl.taskmanagement.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gasl.taskmanagement.bo.TaskManagementBo;
import com.gasl.taskmanagement.dto.Tasks;
import com.gasl.taskmanagement.vo.RequestResponse;
import com.gasl.taskmanagement.vo.TaskFilters;

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
        if (task.getTaskId() == null) {
            task.setCreatedTime(new Date());
		}
		task.setUpdatedTime(new Date());
		Map<String, Object> model = new HashMap<String, Object>();
        String taskId = taskManagementBO.createNewTask(task);
        model.put("taskId", taskId);
        RequestResponse requestResponse = new RequestResponse();
        if(taskId == null)
		{
			requestResponse.setHasError(true);
			requestResponse.setMessage("failed");
		}
        else {
			requestResponse.setHasError(false);
			requestResponse.setMessage("success");
		}
        requestResponse.setModel(taskId);
        return requestResponse;
    }

    @PostMapping("/deletetask")
    public RequestResponse deleteTask(@RequestBody Tasks task) {
        int resp = taskManagementBO.deleteTask(task.getTaskId());
		Map<String, Object> model = new HashMap<String, Object>();
		RequestResponse requestResponse = new RequestResponse();
        if(resp == 200)
		{
			requestResponse.setHasError(false);
			requestResponse.setMessage("success");
		}
        else
		{
			requestResponse.setHasError(true);
			requestResponse.setMessage("failed");
		}
		requestResponse.setModel(task.getTaskId());
		return requestResponse;
	}

    @PostMapping("/fetchtasks")
    public RequestResponse fetchTasks(@RequestBody TaskFilters filters) {
        Map<String, Object> model = new HashMap<String, Object>();
        List<Tasks> tasks = taskManagementBO.fetchTasks(filters);
		RequestResponse requestResponse = new RequestResponse();
		if(tasks == null)
		{
			requestResponse.setHasError(true);
			requestResponse.setMessage("failed");
		}
		else {
			requestResponse.setHasError(false);
			requestResponse.setMessage("success");
		}
		requestResponse.setModel(tasks);
        return requestResponse;
    }

}
