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
 * @Order ע���ִ�����ȼ��ǰ�valueֵ��С����˳��
 * ���û��дorderĬ��ֵ�������������
 * 
 * @author Shuyu.Wang
 *
 */
@Component
@Order(value=1)
public class MyStartupRunner1 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		 System.out.println(">>>>>>>>>>>>>>>��������ִ�У�ִ�м������ݵȲ���11111<<<<<<<<<<<<<");

	}

}
