package com.shuyu.security;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.shuyu.dao.IUserDao;
import com.shuyu.entity.Role;
import com.shuyu.entity.User;

public class MyShiroRealm extends AuthorizingRealm{
	private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private IUserDao userDao; 

    /**
     * Ȩ����֤��Ϊ��ǰ��¼��Subject�����ɫ��Ȩ�� 
     * @see �����ԣ������и÷����ĵ���ʱ��Ϊ����Ȩ��Դ������ʱ 
     * @see �����ԣ�����ÿ�η�������Ȩ��Դʱ����ִ�и÷����е��߼��������������Ĭ�ϲ�δ����AuthorizationCache 
     * @see �����ԣ������������ͬһ��URL������ˢ�£����÷������ᱻ�ظ����ã�Shiro��һ��ʱ������Ҳ����cacheʱ�䣬��ehcache-shiro.xml�����ã����������ʱ������ˢ��ҳ�棬�÷����ᱻִ��
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################ִ��ShiroȨ����֤##################");
        //��ȡ��ǰ��¼������û������ȼ���(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection); 
        //�����ݿ���Ƿ��д˶���
        User user=userDao.findByName(loginName);// ʵ����Ŀ�У�������Ը���ʵ����������棬���������Shiro�Լ�Ҳ����ʱ�������ƣ�2�����ڲ����ظ�ִ�и÷���
        if(user!=null){
            //Ȩ����Ϣ����info,������Ų�����û������еĽ�ɫ��role����Ȩ�ޣ�permission��
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //�û��Ľ�ɫ����
            info.setRoles(user.getRolesName());
            //�û��Ľ�ɫ��Ӧ������Ȩ�ޣ����ֻʹ�ý�ɫ�������Ȩ�ޣ���������п��Բ�Ҫ
            List<Role> roleList=user.getRoleList();
            for (Role role : roleList) {
                info.addStringPermissions(role.getPermissionsName());
            }
            // ���߰������������
            //���һ����ɫ,�������������ϵ����,����֤�����û�ӵ��admin��ɫ    
//            simpleAuthorInfo.addRole("admin");  
            //���Ȩ��  
//            simpleAuthorInfo.addStringPermission("admin:manage");  
//            logger.info("��Ϊ�û�[mike]������[admin]��ɫ��[admin:manage]Ȩ��");
            return info;
        }
        // ����null�Ļ����ͻᵼ���κ��û����ʱ����ص�����ʱ�������Զ���ת��unauthorizedUrlָ���ĵ�ַ
        return null;
    }

    /**
     * ��¼��֤
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken������������ύ�ĵ�¼��Ϣ
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;

        logger.info("��֤��ǰSubjectʱ��ȡ��tokenΪ��" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE)); 

        //����Ƿ��д��û�
        User user=userDao.findByName(token.getUsername());
        if(user!=null){
            // �����ڣ������û���ŵ���¼��֤info�У������Լ�������Աȣ�Shiro��Ϊ���ǽ�������Ա�У��
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }
        return null;
    }

}
