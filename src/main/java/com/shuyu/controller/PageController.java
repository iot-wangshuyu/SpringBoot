package com.shuyu.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;

/**
 * 测试jsp页面
 * @author Shuyu.Wang
 *
 */
@Controller
@RequestMapping(value="page")
public class PageController {
	 // 从 application.properties 中读取配置，如取不到默认值为Hello Shanhy
    @Value("${application.hell:Hello Shanhy}")
    private String hello = "Hello Shanhy";
    
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 默认页<br/>
     * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。
     * 如果加了参数“/”，则只认为是根页面。
     *
     * @return
     * @author SHANHY
     * @create  2016年1月5日
     */
    @RequestMapping(value = {"/","/index"})
    @ApiOperation(value="jsp视图页面测试", notes="接口测")
    public String index(Map<String, Object> model){
        // 直接返回字符串，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
        // 本例为 /WEB-INF/jsp/index.jsp
        model.put("time", new Date());
        model.put("message", this.hello);
        logger.info("访问index页面");
        return "index";
    }

    /**
     * 响应到JSP页面page1
     *
     * @return
     * @author SHANHY
     * @create  2016年1月5日
     */
    @RequestMapping("/page1")
    @ApiOperation(value="jsp视图页面测试", notes="接口测")
    public ModelAndView page1(){
        // 页面位置 /WEB-INF/jsp/page/page.jsp
        ModelAndView mav = new ModelAndView("page1");
        mav.addObject("content", hello);
        return mav;
    }

    /**
     * 响应到JSP页面page1（可以直接使用Model封装内容，直接返回页面字符串）
     *
     * @return
     * @author SHANHY
     * @create  2016年1月5日
     */
    @RequestMapping("/page2")
    @ApiOperation(value="jsp视图页面测试", notes="接口测")
    public String page2(Model model){
        // 页面位置 /WEB-INF/jsp/page/page.jsp
        model.addAttribute("content", hello + "（第二种）");
        return "page1";
    }
    

}
