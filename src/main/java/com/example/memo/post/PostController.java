package com.example.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {
	// http://localhost:80/post/post_list_view
	@GetMapping("/post_list_view")
	public String postListView(Model model) {
		model.addAttribute("viewName", "post/postList");
		return "template/layout";
	}

	// http://localhost:80/post/post_create_view
	@GetMapping("/post_create_view")
	public String postCreateView(Model model) {
		model.addAttribute("viewName", "post/postCreate");
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