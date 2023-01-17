package com.example.memo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.memo.user.model.User;

@Repository
public interface UserDAO {
	public int existLoginId(String loginId);
	
	public void insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("name") String name, 
			@Param("email") String email);
	
	public User selectUserByLoginIdPassword(@Param("loginId") String loginId, @Param("password") String password);
}