package com.gasl.taskmanagement.bo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasl.taskmanagement.dao.TaskManagementDao;
import com.gasl.taskmanagement.dto.Tasks;

@Service
@Transactional
public class TaskManagementBoImpl implements TaskManagementBo {

	@Autowired
	private TaskManagementDao taskManagementDao;

	@Override
	public String createNewTask(Tasks task) {
		try {
			
			task.setCreatedTime(new Date());
			task.setUpdatedTime(new Date());
			
			task = taskManagementDao.saveTask(task);

			if (task != null) {
				Integer taskId = task.getTaskId();
				return taskId.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

}
