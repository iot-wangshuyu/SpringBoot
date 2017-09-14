package com.shuyu.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.shuyu.dao.IScoreDao;
import com.shuyu.security.MyShiroRealm;

/**
 * Shiro ����
 * @author Shuyu.Wang
 *
 */
@Configuration
public class ShiroConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
	
	@Bean
    public EhCacheManager getEhCacheManager() {  
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
        return em;  
    }
	
	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {  
		MyShiroRealm realm = new MyShiroRealm(); 
		realm.setCacheManager(cacheManager);
		return realm;
	}  
	
	/**
	 * ע��DelegatingFilterProxy��Shiro��
	 *
	 * @param dispatcherServlet
	 * @return
	 * @author SHANHY
	 * @create  2016��1��13��
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		//  ��ֵȱʡΪfalse,��ʾ����������SpringApplicationContext����,����Ϊtrue���ʾ��ServletContainer����  
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(myShiroRealm);
//		<!-- �û���Ȩ/��֤��ϢCache, ����EhCache ���� --> 
		dwsm.setCacheManager(getEhCacheManager());
		return dwsm;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}
	
	/**
	 * ����shiroFilterȨ�޿��ƹ��򣨴����ݿ��ȡȻ�����ã�
	 *
	 * @author SHANHY
	 * @create  2016��1��14��
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean, IScoreDao scoreDao){
		/////////////////////// ������Щ��������������õ������ļ��� ///////////////////////
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// authc���ù������µ�ҳ�������֤����ܷ��ʣ�����Shiro���õ�һ��������org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		filterChainDefinitionMap.put("/user", "authc");// ����Ϊ�˲��ԣ�ֻ����/user��ʵ�ʿ��������޸�Ϊ�������ص��������
		// anon������Ӧ�Ĺ����������ǿյ�,ʲô��û��
		logger.info("##################�����ݿ��ȡȨ�޹��򣬼��ص�shiroFilter��##################");
		filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// ����Ϊ�˲��ԣ��̶�д����ֵ��Ҳ���Դ����ݿ�����������ж�ȡ
		
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/**", "anon");//anon �������Ϊ������
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}

	/**
	 * ShiroFilter<br/>
	 * ע����������е� StudentService �� IScoreDao ֻ��һ�����ӣ���Ϊ��������������������ķ�ʽ��ȡ����ط������ݿ�Ķ���
	 * Ȼ���ȡ���ݿ�������ã����õ� shiroFilterFactoryBean �ķ��ʹ����С�ʵ����Ŀ�У���ʹ���Լ���Service������ҵ���߼���
	 *
	 * @param myShiroRealm
	 * @param stuService
	 * @param scoreDao
	 * @return
	 * @author SHANHY
	 * @create  2016��1��14��
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, IScoreDao scoreDao) {
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// �������� SecurityManager  
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// ���������Ĭ�ϻ��Զ�Ѱ��Web���̸�Ŀ¼�µ�"/login.jsp"ҳ��
		shiroFilterFactoryBean.setLoginUrl("/login");
		// ��¼�ɹ���Ҫ��ת������
		shiroFilterFactoryBean.setSuccessUrl("/user");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		loadShiroFilterChain(shiroFilterFactoryBean, scoreDao);
		return shiroFilterFactoryBean;
	}

}
