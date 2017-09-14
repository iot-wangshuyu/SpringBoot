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
 * ʹ��@WebListenerע�⣬ʵ��ServletContextListener�ӿ�
 * servletContext������Ӧ����������������Ч������ϵͳʱ������������������������������Ψһ��
 * 
 * @author Shuyu.Wang
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

	/*
	 * ģ��򵥵�ʵ���������û�ͳ�ƺ�Ӧ�÷�����
	 * ���ʴ�������һ��txt�ļ��У��Ա��ڳ־û����棬����Ŀ������ʱ��Ҳ���Ǵ���servletContext�����ʱ����ü��ط�����
	 * ��txt�ļ��ж�ȡ��ʷ�������� Ȼ��ʹ��setAttribute������������ݴ��뵽�ڴ��С�
	 * ֮��Ӧ�ñ��رգ�servletContext�������ٵ�ʱ����ڴ����µķ��������ݸ���д�뵽txt�ļ���
	 * �Ա����´�����Ӧ�ú������ȡ֮ǰ�ķ�������
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-------ServletContextListener  ServletContex��ʼ��");
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
				System.out.println("-------��ʷ���ʴ�����" + count);
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
		System.out.println("-------ServletContextListener ServletContex����");

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
