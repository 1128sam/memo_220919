package com.example.memo.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.memo.common.FileManagerService;
import com.example.memo.post.Post;
import com.example.memo.post.dao.PostDAO;

@Service
public class PostBO {
//	private Logger logger = LoggerFactory.getLogger(PostBO.class);
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private FileManagerService fms;

	// 글 추가
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

	// 글 수정
	public void updatePost(int userId, String userLoginId, int postId, String subject, String content, MultipartFile file) {
		// 기존 글을 가져온다. (이미지가 교체될 때 기존 이미지 제거를 위해)
		Post post = getPostByPostIdUserId(postId, userId);
		if (post == null) {
			logger.warn("[update post] 수정할 메모가 존재하지 않습니다. postId:{}, userId:{}", postId, userId);
			return;
		}

		// 멀티파일이 비어있지 않다면 업로드 후 imagePath -> 업로드가 성공하면 기존 이미지 제거
		String imagePath = null;
		if (file != null) {
			// upload
			imagePath = fms.saveFile(userLoginId, file);
			
			// upload success, upload가 실패할 수 있으므로 업로드가 성공한 후 제거
			// imagePath가 null이 아니고, 기존 글에 imagePath가 null이 아닐 경우
			if (imagePath != null && post.getImagePath() != null) {
				// 이미지 제거
				fms.deleteFile(post.getImagePath());
			}
		}

		// DB update
		postDAO.updatePostByPostIdUserId(postId, userId, subject, content, file);
	}

	public int deletePostByPostIdUserId(int postId, int userId) {
		// 기존글 가져오기
		Post post = getPostByPostIdUserId(postId, userId);
		if (post == null) {
			logger.warn("[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
			return 0;
		}

		// 업로드 되었던 이미지가 있으면 파일 삭제
		if (post.getImagePath() != null) {
			fms.deleteFile(post.getImagePath());
		}

		// DB delete
		return postDAO.deletePostByPostIdUserId(postId);
	}

	public List<Post> getPostListByUserId(int userId) {
		return postDAO.selectPostListByUserId(userId);
	}
	
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdUserId(postId, userId);
	}
}