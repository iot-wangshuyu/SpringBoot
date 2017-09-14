package com.shuyu.listener;

import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * servletRequest����һ��request���󱻴��������ٵĹ�������Ч��ÿ����һ������ͻᴴ��һ���µ�servletRequest���󣬱���ˢ�������ҳ�桢���Ӧ�õ������ȵ�
 * 
 * @author Shuyu.Wang
 *
 */
public class MyServletRequestListener implements ServletRequestListener {
	/*
	 * servletRequestListener��ʵ��web������ı仯����Ȼ�ˣ�����ֻ�Ǽ򵥵�ʵ�֣�
	 * �����Ҫʵ������ͬһ���û�ˢ��ҳ�治����������Ĺ��ܣ�����Ҫ������Ĵ���
	 * 
	 * ����ͬ����������������servletRequest���󱻽�����ʱ����ó�ʼ�����������ڴ��ж�ȡ
	 * servletContext�����count���ԣ����������ʷ�������� ͬʱ�ڴ˻����ϼ�һ��������servletContext�����count���Ե����ݣ�
	 * ��servletRequest�������ٵ�ʱ���������ʱ�ķ�����ӡ����ǰ������������ͼ򵥵�ʵ����web����������ۼӼ����� 
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("++++++ServletRequestListener request ������");
		System.out.println("++++++requestDestroyed" + "," + new Date());
		System.out.println("++++++��ǰ���ʴ�����" + arg0.getServletContext().getAttribute("count"));

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("++++++ServletRequestListener request ��ʼ��");
		System.out.println("++++++requestInitialized" + "," + new Date());
		Object count = arg0.getServletContext().getAttribute("count");
		Integer cInteger = 0;
		if (count != null) {
			cInteger = Integer.valueOf(count.toString());
		}
		System.out.println("++++++��ʷ���ʴ�������" + count);
		cInteger++;
		arg0.getServletContext().setAttribute("count", cInteger);

	}

}
