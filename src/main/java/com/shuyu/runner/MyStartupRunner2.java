package com.shuyu.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ʵ��Ӧ���У����ǻ�������Ŀ����������ʱ���ȥ����һЩ���ݻ���һЩ�������������� Ϊ�˽�����������⣬spring Boot
 * Ϊ�����ṩ��һ��������ͨ��ʵ�ֽӿ� CommandLineRunner ��ʵ�� Spring
 * BootӦ�ó����������󣬻����CommandLineRunner�ӿڵ�ʵ�����������ǵ�run������Ҳ����
 * ����@Orderע�⣨����ʵ��Order�ӿڣ����涨����CommandLineRunnerʵ��������˳�� ��������ִ��
 * 
 * @author Shuyu.Wang
 *
 */
@Component
@Order(value=2)
public class MyStartupRunner2 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		 System.out.println(">>>>>>>>>>>>>>>��������ִ�У�ִ�м������ݵȲ���22222<<<<<<<<<<<<<");

	}

}
