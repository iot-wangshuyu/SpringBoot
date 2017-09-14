package com.shuyu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * @author Shuyu.Wang
 *
 */
@WebServlet(urlPatterns="/xs/myservlet", description="Servlet��˵��") // ��ָ��name������£�nameĬ��ֵΪ��ȫ·������org.springboot.sample.servlet.MyServlet2
public class Servlet1 extends HttpServlet {
	 private static final long serialVersionUID = -8685285401859800066L;

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        System.out.println(">>>>>>>>>>doGet2()<<<<<<<<<<<");
	        doPost(req, resp);
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        System.out.println(">>>>>>>>>>doPost2()<<<<<<<<<<<");
	        resp.setContentType("text/html");  
	        PrintWriter out = resp.getWriter();  
	        out.println("<html>");  
	        out.println("<head>");  
	        out.println("<title>Hello World</title>");  
	        out.println("</head>");  
	        out.println("<body>");  
	        out.println("<h1>��Һã��ҵ����ֽ�Servlet2</h1>");  
	        out.println("</body>");  
	        out.println("</html>"); 
	    }

}
