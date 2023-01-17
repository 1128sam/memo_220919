package com.example.memo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.memo.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private PostBO postBO;

	/** 
	 * 회원가입 화면
	 * @return
	 * http://localhost:80/user/sign_up_view
	 */
	@GetMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/is_duplicated_id") public Map<String, Boolean>
	 * isDuplicatedId(@RequestParam("loginId") String loginId) { Map<String,
	 * Boolean> result = new HashMap<>(); boolean boolean1 =
	 * postBO.isDuplicatedId(loginId); if (boolean1 != false) {
	 * result.put("is_duplication", true); } else { result.put("is_duplication",
	 * false); } return result; }
	 */

	/** 
	 * 로그인 화면
	 * @return
	 * http://localhost:80/user/sign_in_view
	 */
	@GetMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	@GetMapping("/sign_out")
	public String signOut(HttpSession session) {
		// 로그아웃 => session에 있는 것들을 모두 비운다.
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		return "redirect:/user/sign_in_view"; // 로그아웃 후 로그인 페이지로 redirect
	}
}