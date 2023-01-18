package com.example.memo.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.memo.post.Post;

@Repository
public interface PostDAO {
	public List<Map<String, Object>> selectPostListTest();
	
	public int insertPost(
			@Param("userId") int userId,
			@Param("subect") String subject,
			@Param("content") String content,
			@Param("imagePath") String userimagePath
			);
	
	public List<Post> selectPostListByUserId(int userId);
}