package com.shuyu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shuyu.entity.Student;
import com.shuyu.service.StudentService;

import io.swagger.annotations.ApiOperation;

/**
 * 测试数据库连接
 * @author Shuyu.Wang
 *
 */
@RestController
@RequestMapping("/stu")
public class StudentController {
	
	 private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	    @Autowired
	    private StudentService studentService;

	    @RequestMapping(value="/list", method=RequestMethod.GET)
	    @ApiOperation(value="jdbc数据库连接", notes="接口测")
	    public List<Student> getStus(){
	        logger.info("从数据库读取Student集合");
	        return studentService.getList();
	    }

}
