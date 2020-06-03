package com.gasl.taskmanagement.bo;

import java.util.List;

import com.gasl.taskmanagement.dto.Tasks;
import com.gasl.taskmanagement.vo.TaskFilters;

public interface TaskManagementBo {
	
	public String createNewTask(Tasks task);
	public int deleteTask(int id);
	public List<Tasks> fetchTasks(TaskFilters filters);

}
