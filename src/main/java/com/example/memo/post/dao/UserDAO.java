package com.example.memo.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.memo.user.model.User;

@Repository
public interface UserDAO {
	public boolean existLoginId(String loginId);
	
	public User selectUserByLoginIdPassword(@Param("loginId") String loginId, @Param("password") String password);
}