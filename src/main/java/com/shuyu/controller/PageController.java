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
 * ����jspҳ��
 * @author Shuyu.Wang
 *
 */
@Controller
@RequestMapping(value="page")
public class PageController {
	 // �� application.properties �ж�ȡ���ã���ȡ����Ĭ��ֵΪHello Shanhy
    @Value("${application.hell:Hello Shanhy}")
    private String hello = "Hello Shanhy";
    
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * Ĭ��ҳ<br/>
     * @RequestMapping("/") �� @RequestMapping ���������
     * �����д��������Ϊȫ��Ĭ��ҳ����������404ҳ�棬Ҳ���Զ����ʵ����ҳ�档
     * ������˲�����/������ֻ��Ϊ�Ǹ�ҳ�档
     *
     * @return
     * @author SHANHY
     * @create  2016��1��5��
     */
    @RequestMapping(value = {"/","/index"})
    @ApiOperation(value="jsp��ͼҳ�����", notes="�ӿڲ�")
    public String index(Map<String, Object> model){
        // ֱ�ӷ����ַ��������Ĭ�ϻ�ȥ spring.view.prefix Ŀ¼�µ� ��indexƴ��spring.view.suffix��ҳ��
        // ����Ϊ /WEB-INF/jsp/index.jsp
        model.put("time", new Date());
        model.put("message", this.hello);
        logger.info("����indexҳ��");
        return "index";
    }

    /**
     * ��Ӧ��JSPҳ��page1
     *
     * @return
     * @author SHANHY
     * @create  2016��1��5��
     */
    @RequestMapping("/page1")
    @ApiOperation(value="jsp��ͼҳ�����", notes="�ӿڲ�")
    public ModelAndView page1(){
        // ҳ��λ�� /WEB-INF/jsp/page/page.jsp
        ModelAndView mav = new ModelAndView("page1");
        mav.addObject("content", hello);
        return mav;
    }

    /**
     * ��Ӧ��JSPҳ��page1������ֱ��ʹ��Model��װ���ݣ�ֱ�ӷ���ҳ���ַ�����
     *
     * @return
     * @author SHANHY
     * @create  2016��1��5��
     */
    @RequestMapping("/page2")
    @ApiOperation(value="jsp��ͼҳ�����", notes="�ӿڲ�")
    public String page2(Model model){
        // ҳ��λ�� /WEB-INF/jsp/page/page.jsp
        model.addAttribute("content", hello + "���ڶ��֣�");
        return "page1";
    }
    

}
