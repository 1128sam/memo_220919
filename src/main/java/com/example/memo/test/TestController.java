package com.example.memo.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.memo.dao.PostDAO;

@Controller
public class TestController {

	@Autowired
	private PostDAO postDAO;

	@GetMapping("/test1")
	@ResponseBody
	public String test1() {
		return "Hello world!";
	}

	@GetMapping("/test2")
	@ResponseBody
	public Map<String, Object> test2() {
		Map<String, Object> result = new HashMap<>();
		result.put("키1", 1);
		result.put("키2", 22);
		result.put("키3", 333);
		return result;
	}

	// http://localhost:80/test3
	@GetMapping("/test3")
	public String test3() {
		return "test/test3";
	}

	@GetMapping("/test4")
	@ResponseBody
	public List<Map<String, Object>> test4() {
		return postDAO.selectPostListTest();
	}
}