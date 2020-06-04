package com.gasl.taskmanagement.dao;

import com.gasl.taskmanagement.dto.Users;

public interface UsersDao {

	Users loadByUsername(String userName);

	Users createNewUser(Users user);

}
