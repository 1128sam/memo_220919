package com.example.memo.bo;

import org.springframework.stereotype.Service;

import com.example.memo.dao.PostDAO;

@Service
public class PostBO {
	private PostDAO postDAO;
	
	public boolean isDuplicatedId(String loginId) {
		return postDAO.isDuplicatedId(loginId);
	}
	
	public void addUser(String loginId, String password, String name, String email) {
		postDAO.insertUser(loginId, password, name, email);
	}
}