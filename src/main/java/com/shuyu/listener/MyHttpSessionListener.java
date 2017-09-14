package com.shuyu.listener;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器
 * httpSession则是在一个session会话中生效，在一个session被创建直到失效的过程中都起作用，不过一个启动的应用中httpSession对象可以有多个，
 * 比如同一台电脑两个浏览器访问，就会创建两个httpSession对象
 * @author Shuyu.Wang
 *
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener{
	/*httpSessionListener来实现在线人数的统计
	 * */
	@Override
    public void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("HttpSessionListener Session 被创建");
        System.out.println("sessionCreated" + "," + new Date());
        Object lineCount = arg0.getSession().getServletContext().getAttribute("lineCount");
        Integer count = 0;
        if (lineCount == null) {
            lineCount = "0";
        }
        count = Integer.valueOf(lineCount.toString());
        count++;
        System.out.println("新上线一人，历史在线人数：" + lineCount + "个,当前在线人数有： " + count + " 个");
        arg0.getSession().getServletContext().setAttribute("lineCount", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println("HttpSessionListener  ServletContex初始化");
        System.out.println("sessionDestroyed" + "," + new Date());
        Object lineCount = arg0.getSession().getServletContext().getAttribute("lineCount");
        Integer count = Integer.valueOf(lineCount.toString());
        count--;
        System.out.println("一人下线，历史在线人数：" + lineCount + "个，当前在线人数: " + count + " 个");
        arg0.getSession().getServletContext().setAttribute("lineCount", count);
    }


}
