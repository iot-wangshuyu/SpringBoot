package com.shuyu.listener;

import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * servletRequest是在一个request请求被创建和销毁的过程中生效，每发起一次请求就会创建一个新的servletRequest对象，比如刷新浏览器页面、点击应用的内链等等
 * 
 * @author Shuyu.Wang
 *
 */
public class MyServletRequestListener implements ServletRequestListener {
	/*
	 * servletRequestListener来实现web浏览量的变化，当然了，这里只是简单的实现，
	 * 如果是要实现那种同一个用户刷新页面不增加浏览量的功能，还需要做更多的处理。
	 * 
	 * 这里同样是两个方法，在servletRequest对象被建立的时候调用初始化方法，从内存中读取
	 * servletContext对象的count属性，而后输出历史访问量。 同时在此基础上加一重新设置servletContext对象的count属性的内容，
	 * 当servletRequest对象被销毁的时候调用销毁时的方法打印出当前浏览量，这样就简单的实现了web浏览的量的累加计数。 
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("++++++ServletRequestListener request 被销毁");
		System.out.println("++++++requestDestroyed" + "," + new Date());
		System.out.println("++++++当前访问次数：" + arg0.getServletContext().getAttribute("count"));

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("++++++ServletRequestListener request 初始化");
		System.out.println("++++++requestInitialized" + "," + new Date());
		Object count = arg0.getServletContext().getAttribute("count");
		Integer cInteger = 0;
		if (count != null) {
			cInteger = Integer.valueOf(count.toString());
		}
		System.out.println("++++++历史访问次数：：" + count);
		cInteger++;
		arg0.getServletContext().setAttribute("count", cInteger);

	}

}
