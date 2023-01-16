package com.example.memo.bo;

import org.springframework.stereotype.Service;

import com.example.memo.post.dao.UserDAO;
import com.example.memo.user.model.User;

@Service
public class UserBO {
	private UserDAO userDAO;

	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}

	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}
}