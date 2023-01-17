package com.example.memo.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.memo.common.FileManagerService;
import com.example.memo.post.dao.PostDAO;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private FileManagerService fms;

	public int addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		// 파일 업로드 => 경로
		String imagePath = null;
		if (file != null) {
			// 파일이 있을 때만 업로드 => 이미지 경로를 얻어냄
			imagePath = fms.saveFile(userLoginId, file);
		}

		// return 1;
		// DAO insert
		return postDAO.insertPost(userId, subject, content, imagePath);
	}
}