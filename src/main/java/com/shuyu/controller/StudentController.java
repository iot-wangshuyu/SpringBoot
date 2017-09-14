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
 * �������ݿ�����
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
	    @ApiOperation(value="jdbc���ݿ�����", notes="�ӿڲ�")
	    public List<Student> getStus(){
	        logger.info("�����ݿ��ȡStudent����");
	        return studentService.getList();
	    }

}
