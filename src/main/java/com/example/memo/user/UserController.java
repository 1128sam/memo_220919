package com.example.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.memo.bo.PostBO;

@RequestMapping("/user")
@Controller
public class UserController {
	private PostBO postBO;

	/** 
	 * 회원가입 화면
	 * @return
	 * http://localhost:80/user/sign_up_view
	 */
	@GetMapping("sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	@ResponseBody
	@PostMapping("is_duplicated_id")
	public Map<String, Boolean> isDuplicatedId(@RequestParam("loginId") String loginId) {
		Map<String, Boolean> result = new HashMap<>();
		boolean boolean1 = postBO.isDuplicatedId(loginId);
		if (boolean1 != false) {
			result.put("is_duplication", true);
		} else {
			result.put("is_duplication", false);
		}
		return result;
	}

	/** 
	 * 로그인 화면
	 * @return
	 * http://localhost:80/user/sign_in_view
	 */
	@GetMapping("sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
}