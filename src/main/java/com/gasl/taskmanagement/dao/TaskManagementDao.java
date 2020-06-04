package com.gasl.taskmanagement.dao;

import java.util.List;

import com.gasl.taskmanagement.dto.Tasks;
import com.gasl.taskmanagement.vo.TaskFilters;

public interface TaskManagementDao {
	
	public Tasks saveTask(Tasks task);
	public int deleteTask(int id);
	public List<Tasks> getTasks(TaskFilters filters);
}
