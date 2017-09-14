package com.shuyu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


/**
 * 测试json输出
 * @author Shuyu.Wang
 *
 */
@RestController
@RequestMapping("/test")
public class Hello {

	// 在Java类中创建 logger 实例
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="springboot入门", notes="接口测")
	String home(HttpSession h) {
		logger.info("测试日志");
		return "Hello World SpringBoot!";
	}

	@RequestMapping(value="/info", method=RequestMethod.GET)
	@ApiOperation(value="springboot入门", notes="接口测")
	public Map<String, String> getInfo(@RequestParam String name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		return map;
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ApiOperation(value="springboot入门", notes="接口测")
	public List<Map<String, String>> getList() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for (int i = 1; i <= 5; i++) {
			map = new HashMap<String, String>();
			map.put("name", "Shanhy-" + i);
			list.add(map);
		}
		return list;
	}

}
