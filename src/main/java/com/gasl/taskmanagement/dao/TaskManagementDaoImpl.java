package com.gasl.taskmanagement.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gasl.taskmanagement.dto.Tasks;
import com.gasl.taskmanagement.vo.TaskFilters;


@Repository
public class TaskManagementDaoImpl implements TaskManagementDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tasks saveTask(Tasks task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
        return task;
    }

    @Override
    public int deleteTask(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Tasks tk where tk.taskId =:taskIdParam");
        query.setParameter("taskIdParam", id);
        int count = query.executeUpdate();
        if (count >= 1) {
            return 200;
        } else {
            return 500;
        }
    }

    @Override
    public List<Tasks> getTasks(TaskFilters filters) {
        String queryMain = " from Tasks tk where ";
        int filterCount = 0;
        if (filters.getTaskName() != null) {
            filterCount++;
            queryMain += "tk.taskName LIKE '%" + filters.getTaskName() + "%' AND ";
        }
        if (filters.getStatus() != null) {
            filterCount++;
            queryMain += "tk.status ='" + filters.getStatus() + "' AND ";
        }
        if (filters.getLabel() != null) {
            filterCount++;
            queryMain += "tk.label ='" + filters.getLabel() + "' AND ";
        }
        if (filters.getPriority() != null) {
            filterCount++;
            queryMain += "tk.priority ='" + filters.getPriority() + "' AND ";
        }
        if(filters.getUserId() != null) {
        	filterCount++;
            queryMain += "tk.userId ='" + filters.getUserId()+ "' AND ";
        }

        if (filterCount != 0) {
            queryMain = removeLastChar(queryMain, 4);
        } else {
            queryMain = removeLastChar(queryMain, 7);
        }
        if (filters.getCreatedTime() != null) {
            filterCount++;
            queryMain += " ORDER BY createdTime DESC";
        } else if (filters.getDueDate() != null) {
            filterCount++;
            queryMain += " ORDER BY dueDate DESC";
        } else if (filters.getCompletedDate() != null) {
            filterCount++;
            queryMain += " ORDER BY CompletedDate DESC";
        }
        Query query = sessionFactory.getCurrentSession().createQuery(queryMain);
        List<Tasks> tasks = query.getResultList();
        return tasks;
    }

    public static String removeLastChar(String s, int chars) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - chars));
    }

}
