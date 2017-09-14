package com.shuyu.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 使用@WebListener注解，实现ServletContextListener接口
 * servletContext在整个应用启动到结束中生效，启动系统时创建这个对象，整个过程中这个对象是唯一的
 * 
 * @author Shuyu.Wang
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

	/*
	 * 模拟简单的实现了在线用户统计和应用访问量
	 * 访问次数存在一个txt文件中，以便于持久化保存，当项目启动的时候，也就是创建servletContext对象的时候调用加载方法，
	 * 从txt文件中读取历史访问量， 然后使用setAttribute方法把这个数据存入到内存中。
	 * 之后当应用被关闭，servletContext对象被销毁的时候把内存中新的访问量数据覆盖写入到txt文件，
	 * 以便于下次启动应用后继续读取之前的访问量。
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-------ServletContextListener  ServletContex初始化");
		System.out.println(sce.getServletContext().getServerInfo());
		System.out.println("-------contextInitialized" + "," + new Date());
		File file = new File("E:\\count.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.exists()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
				BufferedReader bReader = new BufferedReader(inputStreamReader);
				String count = bReader.readLine();
				System.out.println("-------历史访问次数：" + count);
				sce.getServletContext().setAttribute("count", count);
				bReader.close();
				
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				int a=Integer.valueOf(count.toString());
				bufferedWriter.write(String.valueOf(a++));
				bufferedWriter.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("-------ServletContextListener ServletContex销毁");

		System.out.println("-------contextDestroyed" + "," + new Date());
		Object count = sce.getServletContext().getAttribute("count");
		File file = new File("E://count.txt");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			int a=Integer.valueOf(count.toString());
			bufferedWriter.write(String.valueOf(a++));
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
