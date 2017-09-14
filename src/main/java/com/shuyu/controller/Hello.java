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
 * ����json���
 * @author Shuyu.Wang
 *
 */
@RestController
@RequestMapping("/test")
public class Hello {

	// ��Java���д��� logger ʵ��
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="springboot����", notes="�ӿڲ�")
	String home(HttpSession h) {
		logger.info("������־");
		return "Hello World SpringBoot!";
	}

	@RequestMapping(value="/info", method=RequestMethod.GET)
	@ApiOperation(value="springboot����", notes="�ӿڲ�")
	public Map<String, String> getInfo(@RequestParam String name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		return map;
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ApiOperation(value="springboot����", notes="�ӿڲ�")
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
