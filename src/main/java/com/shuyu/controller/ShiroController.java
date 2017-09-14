package com.shuyu.controller;

import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shuyu.dao.IUserDao;
import com.shuyu.entity.User;

/**
 * �û�Ȩ�޹������
 * @author Shuyu.Wang
 *
 */
@Controller
public class ShiroController {
	 private static final Logger logger = LoggerFactory.getLogger(ShiroController.class);

	    @Autowired
	    private IUserDao userDao;

	    @RequestMapping(value="/login",method=RequestMethod.GET)
	    public String loginForm(Model model){
	        model.addAttribute("user", new User());
	        return "login";
	    }

	    @RequestMapping(value="/login",method=RequestMethod.POST)
	    public String login(@Valid User user,BindingResult bindingResult,RedirectAttributes redirectAttributes){
	        if(bindingResult.hasErrors()){
	            return "login";
	        }

	        String username = user.getUsername();
	        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
	        //��ȡ��ǰ��Subject  
	        Subject currentUser = SecurityUtils.getSubject();  
	        try {  
	            //�ڵ�����login������,SecurityManager���յ�AuthenticationToken,�����䷢�͸������õ�Realmִ�б������֤���  
	            //ÿ��Realm�����ڱ�Ҫʱ���ύ��AuthenticationTokens������Ӧ  
	            //������һ���ڵ���login(token)����ʱ,�����ߵ�MyRealm.doGetAuthenticationInfo()������,������֤��ʽ����˷���  
	            logger.info("���û�[" + username + "]���е�¼��֤..��֤��ʼ");  
	            currentUser.login(token);  
	            logger.info("���û�[" + username + "]���е�¼��֤..��֤ͨ��");  
	        }catch(UnknownAccountException uae){  
	            logger.info("���û�[" + username + "]���е�¼��֤..��֤δͨ��,δ֪�˻�");  
	            redirectAttributes.addFlashAttribute("message", "δ֪�˻�");  
	        }catch(IncorrectCredentialsException ice){  
	            logger.info("���û�[" + username + "]���е�¼��֤..��֤δͨ��,�����ƾ֤");  
	            redirectAttributes.addFlashAttribute("message", "���벻��ȷ");  
	        }catch(LockedAccountException lae){  
	            logger.info("���û�[" + username + "]���е�¼��֤..��֤δͨ��,�˻�������");  
	            redirectAttributes.addFlashAttribute("message", "�˻�������");  
	        }catch(ExcessiveAttemptsException eae){  
	            logger.info("���û�[" + username + "]���е�¼��֤..��֤δͨ��,�����������");  
	            redirectAttributes.addFlashAttribute("message", "�û�������������������");  
	        }catch(AuthenticationException ae){  
	            //ͨ������Shiro������ʱAuthenticationException�Ϳ��Կ����û���¼ʧ�ܻ��������ʱ���龰  
	            logger.info("���û�[" + username + "]���е�¼��֤..��֤δͨ��,��ջ�켣����");  
	            ae.printStackTrace();  
	            redirectAttributes.addFlashAttribute("message", "�û��������벻��ȷ");  
	        }  
	        //��֤�Ƿ��¼�ɹ�  
	        if(currentUser.isAuthenticated()){  
	            logger.info("�û�[" + username + "]��¼��֤ͨ��(������Խ���һЩ��֤ͨ�����һЩϵͳ������ʼ������)");  
	            return "redirect:/user";
	        }else{  
	            token.clear();  
	            return "redirect:/login";
	        }  
	    }

	    @RequestMapping(value="/logout",method=RequestMethod.GET)  
	    public String logout(RedirectAttributes redirectAttributes ){ 
	        //ʹ��Ȩ�޹����߽����û����˳���������¼��������ʾ��Ϣ
	        SecurityUtils.getSubject().logout();  
	        redirectAttributes.addFlashAttribute("message", "���Ѱ�ȫ�˳�");  
	        return "redirect:/login";
	    } 

	    @RequestMapping(value="/403",method=RequestMethod.GET)
	    public String unauthorizedRole(){
	        logger.info("------û��Ȩ��-------");
	        return "403";
	    }

	    @RequestMapping(value="/user",method=RequestMethod.GET)
	    public String getUserList(Map<String, Object> model){
	        model.put("userList", userDao.getList());
	        return "user";
	    }

	    @RequestMapping(value="/user/edit/{userid}",method=RequestMethod.GET)
	    public String getUserList(@PathVariable int userid){
	        logger.info("------�����û���Ϣ�޸�-------");
	        return "user_edit";
	    }

}
