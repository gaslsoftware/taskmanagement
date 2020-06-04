package com.gasl.taskmanagement.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gasl.taskmanagement.dao.UsersDao;
import com.gasl.taskmanagement.definition.UsersPrincipal;
import com.gasl.taskmanagement.dto.Users;

@Service
@Transactional
public class UsersDetailsService implements UserDetailsService {

	@Autowired
	UsersDao usersDao;

	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersDao.loadByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UsersPrincipal(user);
	}

	public Users createNewUser(Users user) {
		String password = user.getPassword();
		if (password != null && !password.equals("")) {
			String encryptedPassword = bcryptPasswordEncoder.encode(password);
			user.setPassword(encryptedPassword);
		}
		return usersDao.createNewUser(user);
	}

}
