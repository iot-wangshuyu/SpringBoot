package com.shuyu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuyu.dao.IScoreDao;
import com.shuyu.entity.Score;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/score")
public class ScoreController {
	
	 private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);

	    @Autowired
	    private IScoreDao scoreService;
	    @ResponseBody
	    @RequestMapping(value ="/scoreList",method = RequestMethod.GET)
	    @ApiOperation(value="JPA数据连接", notes="JPA数据连接接口测")
	    public List<Score> getScoreList(){
	        logger.info("从数据库读取Score集合");
	        // 测试更新数据库
	        logger.info("更新的行数：" + scoreService.updateScoreById(88.8f, 2));
	        scoreService.delete(1);

	        return scoreService.getList();
	    }

}
