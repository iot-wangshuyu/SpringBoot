package com.shuyu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import com.shuyu.filter.MyFilter;
import com.shuyu.listener.MyHttpSessionListener;
import com.shuyu.listener.MyServletContextListener;
import com.shuyu.listener.MyServletRequestListener;

@SpringBootApplication
@ServletComponentScan
public class SpringBootSampleApplication {
	
	/**
     * ʹ�ô���ע��Servlet������Ҫ@ServletComponentScanע�⣩
     * @return
     */
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        return new ServletRegistrationBean(new Servlet2(), "/xs/*");// ServletNameĬ��ֵΪ����ĸСд����myServlet
//    }

	/**
	 * �޸�DispatcherServletĬ�����ã���Ҫ@ServletComponentScanע�⣩
	 * @param dispatcherServlet
	 * @return
	 */
//	@Bean
//	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//		registration.getUrlMappings().clear();
//		registration.addUrlMappings("*.do");
//		registration.addUrlMappings("*.json");
//		return registration;
//	}
	
	@Bean
	public FilterRegistrationBean myFilter(){
		
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new MyFilter());  
//		filterRegistrationBean.setFilter(new MShiroFilterFactoryBean());  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/*");//����·����������Ӷ��  
        filterRegistrationBean.setUrlPatterns(urlPatterns);  
        filterRegistrationBean.setOrder(1); 
		return filterRegistrationBean;
		
	}
	
	@Bean
	public ServletListenerRegistrationBean myListener(DispatcherServlet dispatcherServlet){
		ServletListenerRegistrationBean servletListenerRegistrationBean=new ServletListenerRegistrationBean();
		servletListenerRegistrationBean.setListener(new MyHttpSessionListener());  
		servletListenerRegistrationBean.setListener(new MyServletContextListener());  
		servletListenerRegistrationBean.setListener(new MyServletRequestListener());  
		return servletListenerRegistrationBean;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleApplication.class, args);
	}

}
