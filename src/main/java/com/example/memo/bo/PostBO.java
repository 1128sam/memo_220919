package com.example.memo.bo;

import org.springframework.stereotype.Service;

import com.example.memo.post.dao.PostDAO;

@Service
public class PostBO {
	private PostDAO postDAO;
	
	public boolean isDuplicatedId(String loginId) {
		return postDAO.isDuplicatedId(loginId);
	}
}