package com.example.memo.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.memo.dao.UserDAO;
import com.example.memo.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;

	public int existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
	
	public void addUser(String loginId, String password, String name, String email) {
		userDAO.insertUser(loginId, password, name, email);
	}

	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}
}