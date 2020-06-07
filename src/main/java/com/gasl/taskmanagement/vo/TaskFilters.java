package com.gasl.taskmanagement.vo;

import java.util.Date;

public class TaskFilters {
	private String taskName;
	private Date createdTime;
	private Integer dueDate;
	private String status;
	private String label;
	private Integer completedDate;
	private Integer priority;
	private Integer userId;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDueDate() {
		return dueDate;
	}

	public void setDueDate(Integer dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Integer completedDate) {
		this.completedDate = completedDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
