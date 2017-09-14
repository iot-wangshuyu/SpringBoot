package com.shuyu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.shuyu.interceptor.MyInterceptor;
import com.shuyu.interceptor.MyInterceptor2;

/**
 * Web�����У����ǳ���ʹ�� Filter ��������web���⣬������ʹ��Spring�ṩ��HandlerInterceptor������������
 * 
 * HandlerInterceptor �Ĺ��ܸ����������ƣ������ṩ����ϸ�ĵĿ�����������request����Ӧ֮ǰ��request����Ӧ֮��
 * ��ͼ��Ⱦ֮ǰ�Լ�requestȫ������֮�����ǲ���ͨ���������޸�request���ݣ����ǿ���ͨ���׳��쳣�����߷���false������ͣrequest��ִ�С�
 * 
 * ʵ�� UserRoleAuthorizationInterceptor ���������У�
 * ConversionServiceExposingInterceptor CorsInterceptor LocaleChangeInterceptor
 * PathExposingHandlerInterceptor ResourceUrlProviderExposingInterceptor
 * ThemeChangeInterceptor UriTemplateVariablesHandlerInterceptor
 * UserRoleAuthorizationInterceptor
 * 
 * ���� LocaleChangeInterceptor �� ThemeChangeInterceptor �Ƚϳ��á�
 * 
 * ����������Ҳ�ܼ򵥣�Spring Ϊʲô�ṩ�˻�����WebMvcConfigurerAdapter ������ֻ��Ҫ��д addInterceptors
 * �������ע����������
 * 
 * ʵ���Զ���������ֻ��Ҫ3����
 * 1�����������Լ����������ಢʵ�� HandlerInterceptor �ӿڡ�
 * 2������һ��Java��̳�WebMvcConfigurerAdapter������д addInterceptors ������
 * 3��ʵ���������Զ������������Ȼ�󽫶����ֶ���ӵ����������У���addInterceptors��������ӣ���
 * ���ǿ��һ�㣺ֻ�о���DispatcherServlet �����󣬲Ż������������������Զ����Servlet �����ǲ��ᱻ���صģ����������Զ����Servlet��ַ 
 * http://localhost:8080/boot/xs/myservlet �ǲ��ᱻ���������صġ����Ҳ����������ĸ�Servlet ֻҪ���Ϲ������Ĺ��˹��򣬹�������������
 * @author Shuyu.Wang
 *
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// ������������һ����������
		// addPathPatterns ����������ع���
		// excludePathPatterns �û��ų�����
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
