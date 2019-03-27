package com.yanchong.blog.Shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/login");//登录连接
        shiroFilterFactoryBean.setSuccessUrl("/index");//登录成功后跳转的连接
        shiroFilterFactoryBean.setUnauthorizedUrl("/pages/403"); //未授权跳转页面

        //定义shiro过滤链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置退出
        filterChainDefinitionMap.put("/logout", "logout"); //配置退出
        // <!-- 过滤链定义，从上向下顺序执行，/**放在最下面，过滤链的最后一关，表示除去以上各环节，剩余url的都需要验证 -->
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/register","anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/adm/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager(){
        //使用默认的安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(securityRealm());  //加入自定义的安全领域
        return securityManager;
    }

    @Bean
    public MySecurityRealm securityRealm(){
        MySecurityRealm securityRealm = new MySecurityRealm();
        securityRealm.setCredentialsMatcher(hashedCredentialsMatcher());//凭证匹配器
        securityRealm.setCachingEnabled(false);//不使用缓存
        return securityRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher  hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//使用MD5散列算法
        hashedCredentialsMatcher.setHashIterations(1);//散列次数，这里等于1次MD5
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);  //散列后密码为16进制，要与生成密码时一致。false 表示Base64编码
        return hashedCredentialsMatcher;
    }
}
