package com.shuyu.listener;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ������
 * httpSession������һ��session�Ự����Ч����һ��session������ֱ��ʧЧ�Ĺ����ж������ã�����һ��������Ӧ����httpSession��������ж����
 * ����ͬһ̨����������������ʣ��ͻᴴ������httpSession����
 * @author Shuyu.Wang
 *
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener{
	/*httpSessionListener��ʵ������������ͳ��
	 * */
	@Override
    public void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("HttpSessionListener Session ������");
        System.out.println("sessionCreated" + "," + new Date());
        Object lineCount = arg0.getSession().getServletContext().getAttribute("lineCount");
        Integer count = 0;
        if (lineCount == null) {
            lineCount = "0";
        }
        count = Integer.valueOf(lineCount.toString());
        count++;
        System.out.println("������һ�ˣ���ʷ����������" + lineCount + "��,��ǰ���������У� " + count + " ��");
        arg0.getSession().getServletContext().setAttribute("lineCount", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println("HttpSessionListener  ServletContex��ʼ��");
        System.out.println("sessionDestroyed" + "," + new Date());
        Object lineCount = arg0.getSession().getServletContext().getAttribute("lineCount");
        Integer count = Integer.valueOf(lineCount.toString());
        count--;
        System.out.println("һ�����ߣ���ʷ����������" + lineCount + "������ǰ��������: " + count + " ��");
        arg0.getSession().getServletContext().setAttribute("lineCount", count);
    }


}
