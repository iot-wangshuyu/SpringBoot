package com.shuyu.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/api/test")
public class TestController {
	@ResponseBody
    @RequestMapping(value = "/show", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)// ����ָ��RequestMethod�������ָ��Swagger�������RequestMethod���������ʵ��Ӧ���У�����ָ����������Ҳʹ�ӿڸ�Ϊ�Ͻ���
    @ApiOperation(value="���Խӿ�", notes="���Խӿ���ϸ����")
    public String show(
            @ApiParam(required=true, name="name", value="����")
            @RequestParam(name = "name", required=true) String stuName){
        return "success";
    }

}
