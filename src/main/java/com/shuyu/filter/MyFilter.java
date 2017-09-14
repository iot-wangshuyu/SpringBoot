package com.shuyu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * ʹ��ע���ע������
 * @WebFilter��һ��ʵ����javax.servlet.Filter�ӿڵ��ඨ��Ϊ������
 * ����filterName����������������,��ѡ
 * ����urlPatternsָ��Ҫ���� ��URLģʽ,Ҳ��ʹ������value������.(ָ��Ҫ���˵�URLģʽ�Ǳ�ѡ����)
 * @author Shuyu.Wang
 *
 */
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFilter implements Filter {
	
	 @Override
	    public void destroy() {
	        System.out.println("����������");
	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {
	        System.out.println("ִ�й��˲���");
	        chain.doFilter(request, response);
	    }

	    @Override
	    public void init(FilterConfig config) throws ServletException {
	        System.out.println("��������ʼ��");
	    }

}
