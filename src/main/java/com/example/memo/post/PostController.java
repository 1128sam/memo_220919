package com.example.memo.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.memo.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	@Autowired
	private PostBO postBO;
	
	// http://localhost:80/post/post_list_view
	@GetMapping("/post_list_view")
	public String postListView(
			@RequestParam(value="prevId", required=false) Integer prevIdParam,
			@RequestParam(value="nextId", required=false) Integer nextIdParam,
			Model model, HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/sign_in_view";
		}

		int prevId = 0, nextId = 0;
		List<Post> postList = postBO.getPostListByUserId(userId, prevIdParam, nextIdParam);
		if (postList.isEmpty() == false) { // postList가 비었을 때 에러 방지
			prevId = postList.get(0).getId(); // 가져온 리스트 중 가장 앞쪽(큰 id)
			nextId = postList.get(postList.size() - 1).getId(); // 가져온 리스트 중 가장 뒷쪽(작은 id)
			
			// 이전 방향의 끝인가? postList의 0 index 값(prevId)과 post table의 가장 큰 값이 같으면 마지막 페이지
			if (postBO.isPrevLastPage(prevId, userId)) { // 마지막 페이지일 때
				prevId = 0;
			}

			// 다음 방향의 끝인가? postList의 마지막 index값(nextId)와 post table의 가장 작은 값이 같으면 마지막 페이지
			if (postBO.isNextLastPage(nextId, userId)) {
				nextId = 0;
			}
		}

		model.addAttribute("prevId", prevId); // 가져온 리스트 중 가장 앞쪽(큰 id)
		model.addAttribute("nextId", nextId); // 가져온 리스트 중 가장 뒷쪽(작은 id)
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "post/postList");
		return "template/layout";
	}

	// 글쓰기 화면
	// http://localhost:80/post/post_create_view
	@GetMapping("/post_create_view")
	public String postCreateView(Model model) {
		model.addAttribute("viewName", "post/postCreate");
		return "template/layout";
	}

	@GetMapping("/post_detail_view")
	public String postDetailView(@RequestParam("postId") int postId, 
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		// DB select by userId, postId
		Post post = postBO.getPostByPostIdUserId(postId, userId);
		
		model.addAttribute("post", post);
		model.addAttribute("viewName", "post/postDetail");
		return "template/layout";
	}
}


/*
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




package com.example.memo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {

	public List<Map<String, Object>> selectPostListTest();
	
	public boolean isDuplicatedId(String loginId);
	
	public void insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("name") String name, 
			@Param("email") String email);
}
*/